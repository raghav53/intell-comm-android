package com.intell.comm.workManger

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.intell.comm.base.repository.MainApiRepository
import com.intell.comm.localDatabase.AppDatabase
import com.intell.comm.localDatabase.post.PostModel
import com.intell.comm.network.POST_RENT_SHEET_ID
import com.intell.comm.network.getAllRentAndPostRentLink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.ConnectException
import java.net.UnknownHostException
import kotlin.collections.HashMap

class UploadPendingPostOnServer(
    private val mainApiRepository: MainApiRepository,
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val database = AppDatabase.getInstance(applicationContext)

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val postRentModelDb = database.postDao().getStatusPostRentModel(false)
        try {
            if (postRentModelDb.isNotEmpty()) {
                for (post in postRentModelDb) {
                    if (post.addOrUpdate == 0) {
                        addPost(post)
                    } else if (post.addOrUpdate == 1) {
                        updatePost(post)
                    }

                }
            }
            Result.success()
        } catch (throwable: Throwable) {
            Log.e("UploadPendingPost", "${throwable.message} Error applying blur")
            Result.failure()
        }
    }

    private suspend fun addPost(postModel: PostModel) {
        val map: HashMap<String, String> = HashMap()
        map["method"] = "addPostRent"
        uploadOnServer(map, postModel)
    }

    private suspend fun updatePost(postModel: PostModel) {
        val map: HashMap<String, String> = HashMap()
        map["method"] = "updatePostRent"
        uploadOnServer(map, postModel)
    }

    private suspend fun uploadOnServer(map: HashMap<String, String>, postModel: PostModel) {
        val jsonObject = JSONObject()
        jsonObject.put("uuid", postModel.uuid)

        map["sheetId"] = POST_RENT_SHEET_ID
        map["body"] = jsonObject.toString()

        mainApiRepository.simpleApiResponsePost(getAllRentAndPostRentLink, map)
            .catch { e ->
                if (e is UnknownHostException || e is javax.net.ssl.SSLException || e is ConnectException) {
                    Log.e(TAG, "addPost_Error: No Internet Connection")
                } else {
                    Log.e(TAG, "addPost_Error: " + e.message)
                }
            }.collect { data ->
                Log.e(TAG, "addPost_Success: " + data.message)
                postModel.uploadStatus = true
                if (postModel.addOrUpdate==0){
                    postModel.addOrUpdate = 2//0=add, 1=update, 2= no need action
                    database.postDao().insertPostRentModel(postModel)
                }else {
                    postModel.addOrUpdate = 2//0=add, 1=update, 2= no need action
                    database.postDao().updatePostRentModel(postModel)
                }
            }
    }

    companion object {
        private const val TAG = "UploadPendingPost"
    }
}