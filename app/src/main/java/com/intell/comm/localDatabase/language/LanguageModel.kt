package com.intell.comm.localDatbase.language

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * [LanguageResponse] class only handle language list
 * and [LanguageModel] store all language in database
 * */

@Entity(tableName = "language_model")
data class LanguageModel(
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
     * @param code
     */
    /***/

    val id: String,
    val code: String?,
    val createdAt: String?,

    @Embedded val translations: Translations?

)