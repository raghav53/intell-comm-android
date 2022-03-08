package com.intell.comm.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.intell.comm.localDatbase.language.LanguageModel
import com.intell.comm.utils.Converters
import com.intell.comm.utils.DATABASE_NAME
import com.intell.comm.localDatabase.post.PostModel
import com.intell.comm.localDatabase.dao.PostModelDao
import com.intell.comm.localDatabase.dao.LanguageDao
import com.intell.comm.utils.DATABASE_VERSION

@Database(entities = [LanguageModel::class, PostModel::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun languageDao(): LanguageDao
    abstract fun postDao(): PostModelDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}