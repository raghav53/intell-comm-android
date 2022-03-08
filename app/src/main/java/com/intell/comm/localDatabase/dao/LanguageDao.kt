package com.intell.comm.localDatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.intell.comm.localDatbase.language.LanguageModel

@Dao
interface LanguageDao {

    @Insert
    suspend fun insertLanguageModel(language: LanguageModel)

    @Update
    suspend fun updateLanguageModel(language: LanguageModel)

    @Query("DELETE FROM language_model WHERE code=:lanCode")
    suspend fun deleteLanguageModel(lanCode: String)

    @Query("SELECT * FROM language_model")
    fun getLanguageList(): LiveData<List<LanguageModel>>

    @Query("SELECT * FROM language_model WHERE code=:lanCode LIMIT 1")
    fun getLanguageModel(lanCode: String): LiveData<LanguageModel>

    @Query("DELETE FROM language_model")
    suspend fun deleteAllLanguageModel()

}