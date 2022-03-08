package com.intell.comm.localDatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.intell.comm.localDatabase.post.PostModel

@Dao
interface PostModelDao {

    @Insert
    suspend fun insertPostRentModel(postModel: PostModel)

    @Update
    suspend fun updatePostRentModel(postModel: PostModel)

    @Query("SELECT * FROM post_model")
    fun getAllPostRentModel(): LiveData<List<PostModel>>

    @Query("SELECT * FROM post_model WHERE uploadStatus=:isUploaded")
    fun getStatusPostRentModelLive(isUploaded: Boolean): LiveData<List<PostModel>>

    @Query("SELECT * FROM post_model WHERE uploadStatus=:isUploaded")
    fun getStatusPostRentModel(isUploaded: Boolean): List<PostModel>

    @Query("SELECT * FROM post_model WHERE uuid=:uuid LIMIT 1")
    fun getUUID(uuid: String): LiveData<PostModel>

    @Query("DELETE FROM post_model WHERE uuid=:uuid")
    suspend fun deletePostRentModel(uuid: String)

    @Query("DELETE FROM post_model")
    suspend fun deleteAllPostRentModel()

}