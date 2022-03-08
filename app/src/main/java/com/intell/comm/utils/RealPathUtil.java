package com.intell.comm.utils;

import static com.intell.comm.utils.UtilsKt.getMimeType;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RealPathUtil {

    public static String getRealPath(Context context, Uri fileUri) {
        File file = new File(context.getCacheDir(), "tmp");
        String filePath = file.getAbsolutePath();
        FileInputStream input = null;
        FileOutputStream output = null;

        try {
            ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(fileUri, "r");
            if (pfd == null)
                return null;

            FileDescriptor fd = pfd.getFileDescriptor();
            input = new FileInputStream(fd);
            output = new FileOutputStream(filePath);
            int read;
            byte[] bytes = new byte[4096];
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }

            input.close();
            output.close();
            return new File(filePath).getAbsolutePath();
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static File createCopyAndReturnRealPath(
            @NonNull Context context, @NonNull Uri uri) {
        final ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null)
            return null;


        String mimeType = getMimeType(context, uri);
        // Create file path inside app's data dir
        String filePath = context.getApplicationInfo().dataDir + File.separator
                + System.currentTimeMillis() + "." + mimeType;

        File file = new File(filePath);
        try {
            InputStream inputStream = contentResolver.openInputStream(uri);
            if (inputStream == null)
                return null;

            OutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0)
                outputStream.write(buf, 0, len);

            outputStream.close();
            inputStream.close();


        } catch (IOException ignore) {
            return null;
        }

        return file;
    }

    public static String resizePhotoAndSaveToDisk(Context context, Uri uri) {

        File file = createCopyAndReturnRealPath(context, uri);

        Uri photoUri = Uri.fromFile(file);

        Bitmap rawTakenImage = BitmapFactory.decodeFile(photoUri.getPath());

        Bitmap resizedBitmap = BitmapScaler.scaleToFitWidth(rawTakenImage, 400);

        // Configure byte output stream
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        // Compress the image further
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

        String mimeType = getMimeType(context, uri);
        // Create file path inside app's data dir
        String filePath = context.getApplicationInfo().dataDir + File.separator
                + System.currentTimeMillis() + "." + mimeType;

        File resizedFile = new File(filePath);
        try {
            resizedFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(resizedFile);
            // Write the bytes of the bitmap to file
            fos.write(bytes.toByteArray());
            fos.close();
        } catch (IOException ignore) {
            return null;
        }

        return resizedFile.getAbsolutePath();
    }

}
