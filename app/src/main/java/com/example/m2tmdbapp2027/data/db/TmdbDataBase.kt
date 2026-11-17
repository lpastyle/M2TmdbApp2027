package com.example.m2tmdbapp2027.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.m2tmdbapp2027.BuildConfig
import java.util.concurrent.Executors

@Database (
    entities = [SocialBarEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TmdbDataBase : RoomDatabase() {

    abstract fun socialBarDao (): SocialBarDao

    companion object {
        private val DB_NAME = "tmdpapp.db"

        @Volatile
        private var instance: TmdbDataBase? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    TmdbDataBase::class.java,
                    DB_NAME
                ) /* .apply {
                    if (BuildConfig.DEBUG) {
                        setQueryCallback({ sql, args ->
                            Log.d("RoomSQL", "SQL: $sql  args: $args")},
                            Executors.newSingleThreadExecutor())
                    }
                } */ .build().also {
                        instance = it
                    }
            }
    }
}