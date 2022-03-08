package com.intell.comm.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentActivity
import com.intell.comm.R
import com.intell.comm.base.viewModel.BaseViewModel
import com.intell.comm.base.views.BaseActivity
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

fun validateEmail(activity: Activity, editText: EditText): String {
    return if (editText.text.toString().trim().length < 6) {
        (activity as BaseActivity<*, *>).setViewBackgroundDrawable(
            editText,
            R.drawable.edit_text_selected_red
        )
        ""
    } else if (!Patterns.EMAIL_ADDRESS.matcher(
            editText.text.toString().trim()
        )
            .matches()
    ) {
        (activity as BaseActivity<*, *>).setViewBackgroundDrawable(
            editText,
            R.drawable.edit_text_selected_red
        )
        ""
    } else {
        (activity as BaseActivity<*, *>).setViewBackgroundDrawable(
            editText,
            R.drawable.edit_text_selected_green
        )
        editText.text.toString().trim()
    }
}


fun validateText(activity: Activity, editText: EditText): String {
    return if (TextUtils.isEmpty(editText.text.toString().trim())) {
        (activity as BaseActivity<*, *>).setViewBackgroundDrawable(
            editText,
            R.drawable.edit_text_selected_red
        )
        ""
    } else {
        (activity as BaseActivity<*, *>).setViewBackgroundDrawable(
            editText,
            R.drawable.edit_text_selected_green
        )
        editText.text.toString().trim()
    }
}

fun checkIsEmpty(viewModel: BaseViewModel, string: String, view: View): Boolean {
    return if (TextUtils.isEmpty(string)) {
        viewModel.messageUtils.warning(view, "Please enter required fields")
        true
    } else {
        false
    }
}


fun hideSoftKeyboard(activity: FragmentActivity, focusedView: View) {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(focusedView.windowToken, 0)
}

fun htmlFormattedString(stringToBeFormatted: String): Spanned {
    return HtmlCompat.fromHtml(stringToBeFormatted, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun perfectDecimal(string: String, MAX_BEFORE_POINT: Int, MAX_DECIMAL: Int): String? {
    var text = string
    if (text[0] == '.') text = "0$text"
    val max = text.length
    var rFinal = "";
    var after = false
    var index = 0;
    var up = 0;
    var decimal = 0
    var char: Char
    while (index < max) {
        char = text[index]
        if (char != '.' && !after) {
            up++
            if (up > MAX_BEFORE_POINT) return rFinal
        } else if (char == '.') {
            after = true
        } else {
            decimal++
            if (decimal > MAX_DECIMAL) return rFinal
        }
        rFinal += char
        index++
    }
    return rFinal
}

fun htmlFormattedStringColor(
    stringToBeFormatted: String?,
    replace1: String,
    replace2: String
): Spanned {
    val string = if (stringToBeFormatted == null) {
        ""
    } else {
        stringToBeFormatted
    }
    val sss = string.replace("%%%", replace2)
    val ss = sss.replace("%%", replace1)

    val spannable: Spannable = SpannableString(ss)
    if (ss.indexOf(replace1) < 0) {
        return spannable
    }
    spannable.setSpan(
        ForegroundColorSpan(Color.parseColor("#FF00467F")),
        ss.indexOf(replace1),
        ss.indexOf(replace1) + replace1.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    if (ss.indexOf(replace2) < 0) {
        return spannable
    }
    spannable.setSpan(
        ForegroundColorSpan(Color.parseColor("#FF00467F")),
        ss.indexOf(replace2),
        ss.indexOf(replace2) + replace2.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannable
}

fun htmlFormattedStringColor(
    stringToBeFormatted: String?,
    replace: String,
): Spanned {
    val string = if (stringToBeFormatted == null) {
        ""
    } else {
        stringToBeFormatted
    }

    val ss = string.replace("%%", replace)
    val spannable: Spannable = SpannableString(ss)
    if (ss.indexOf(replace) < 0) {
        return spannable
    }
    spannable.setSpan(
        ForegroundColorSpan(Color.parseColor("#FF00467F")),
        ss.indexOf(replace),
        ss.indexOf(replace) + replace.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )//SPAN_INCLUSIVE_INCLUSIVE

    return spannable
}

fun htmlFormattedString(
    stringToBeFormatted: String?,
    replace1: String,
    replace2: String
): Spanned {
    val string = if (stringToBeFormatted == null) {
        ""
    } else {
        stringToBeFormatted
    }
    val sss = string.replace("%%%", "<b>$replace2</b>")
    val ss = sss.replace("%%", "<b>$replace1</b>")
    return HtmlCompat.fromHtml(ss, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun htmlFormattedString(
    stringToBeFormatted: String?,
    replace: String,
): Spanned {
    val string = if (stringToBeFormatted == null) {
        ""
    } else {
        stringToBeFormatted
    }
    val ss = string.replace("%%", "<b>$replace</b>")
    return HtmlCompat.fromHtml(ss, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun stringFormattedString(
    stringToBeFormatted: String?,
    replace1: String,
    replace2: String
): String {
    val string = if (stringToBeFormatted == null) {
        ""
    } else {
        stringToBeFormatted
    }
    val sss = string.replace("%%%", "<b>$replace2</b>")
    val ss = sss.replace("%%", "<b>$replace1</b>")
    return ss
}

fun stringFormattedString(
    stringToBeFormatted: String?,
    replace: String,
): String {
    val string = if (stringToBeFormatted == null) {
        ""
    } else {
        stringToBeFormatted
    }
    val ss = string.replace("%%", "<b>$replace</b>")
    return ss
}

fun getMimeType(context: Context, uri: Uri): String? {
    val contentResolver = context.contentResolver
    val mime = MimeTypeMap.getSingleton()
    return mime.getExtensionFromMimeType(contentResolver.getType(uri))
}

fun capitalizeWords(
    string: String,
    delimiter: String = " ",
    separator: String = " "
): String {
    return string.split(delimiter).joinToString(separator = separator) {
        it.lowercase().replaceFirstChar { char -> char.titlecase() }
    }
}

fun getCurrentTime(): String {
    val cal: Calendar = Calendar.getInstance()
    val dateFormatForServer =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return dateFormatForServer.format(cal.time)
}

fun getTimeOffsetGMT(): String {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault())
    return SimpleDateFormat("Z").format(cal.time)
}

fun getTimeOffsetMinute(): Int {
    val timezone = TimeZone.getDefault()
    val seconds = timezone.getOffset(Calendar.ZONE_OFFSET.toLong()) / 1000
    return (seconds / 60)
}

fun getConvertedDistanceUnit(
    distance: Float,
    savedTimeUnit: String,
    unitInConvert: String
): String {

    if (savedTimeUnit == unitInConvert) {
        return String.format("%.2f", distance)
    } else if (unitInConvert == "KM") {
        return String.format("%.2f", (distance * 1.60934).toFloat())
    } else if (unitInConvert == "Miles") {
        return String.format("%.2f", (distance / 1.60934).toFloat())
    } else return ""
}

fun getEncodeBase64StringPath(filePath: String): String? {

    // give your image file url in mCurrentPhotoPath
    val bitmap = BitmapFactory.decodeFile(filePath)
    val byteArrayOutputStream = ByteArrayOutputStream()
    // In case you want to compress your image, here it's at 40%
    bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream)
    val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
    return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
}

fun getEncodeBase64Bitmap(bitmap: Bitmap): String {

    // give your image file url in mCurrentPhotoPath
    val byteArrayOutputStream = ByteArrayOutputStream()
    // In case you want to compress your image, here it's at 40%
    bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream)
    val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
    return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
}

fun getDecodeBase64Image(completeImageData: String): Bitmap {

    // In case you're storing into aws or other places where we have extension stored in the starting.
    val imageDataBytes = completeImageData.substring(completeImageData.indexOf(",") + 1)
    val stream: InputStream =
        ByteArrayInputStream(
            android.util.Base64.decode(
                imageDataBytes.toByteArray(),
                android.util.Base64.DEFAULT
            )
        )
    return BitmapFactory.decodeStream(stream)
}


private fun performCrop(
    activity: Activity,
    picPath: String,
    picUri: Uri? = null,
    aspectX: Int = 1,
    aspectY: Int = 1,
    outputX: Int = 300,
    outputY: Int = 300
) {
    try {
        //Start Crop Activity
        val cropIntent = Intent("com.android.camera.action.CROP")
        val list: List<ResolveInfo> = activity.packageManager.queryIntentActivities(cropIntent, 0)
        if (list.isEmpty()) {
            Toast.makeText(activity, "Can not find image crop app", Toast.LENGTH_SHORT).show()
            return
        }
        // indicate image type and Uri
        if (picUri == null) {
            val f = File(picPath)
            val contentUri = Uri.fromFile(f)
            cropIntent.setDataAndType(contentUri, "image/*")
        } else {
            cropIntent.setDataAndType(picUri, "image/*")
        }
        // set crop properties
        cropIntent.putExtra("crop", "true")
        // indicate aspect of desired crop
        cropIntent.putExtra("aspectX", aspectX)
        cropIntent.putExtra("aspectY", aspectY)
        // indicate output X and Y
        cropIntent.putExtra("outputX", outputX)
        cropIntent.putExtra("outputY", outputY)

        // retrieve data on return
        cropIntent.putExtra("return-data", true)
        //setComponent
        val res = list[0]
        cropIntent.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
        // start the activity - we handle returning in onActivityResult
        ActivityCompat.startActivityForResult(activity, cropIntent, 11, null)
    } // respond to users whose devices do not support the crop action
    catch (intentNotFound: ActivityNotFoundException) {
        // display an error message
        val errorMessage = "your device doesn't support the crop action!"
        val toast = Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun cropImage() {


    }
}


