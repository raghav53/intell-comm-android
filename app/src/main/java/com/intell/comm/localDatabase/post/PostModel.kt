package com.intell.comm.localDatabase.post

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_model")
data class PostModel(
    /**
     * @param idDb is auto generate id
     * which help us for search local database items using this
     */
    /***/
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_db")
    var idDb: Long,

    /**use for search keyword for database query
     * @param id and
     * @param uuid is Unique local and live database
     */
    /***/
//these are key of table
// id	userId	jsonImage	title	description	rentPrice	mobile	provideService	addressJson	extraJsonData	uuid
    val id: Int,
    val uuid: String,// all work depend upon uuid on server side
    var uploadStatus:Boolean,
    var addOrUpdate:Int//0=add, 1=update, 2= no need action
)