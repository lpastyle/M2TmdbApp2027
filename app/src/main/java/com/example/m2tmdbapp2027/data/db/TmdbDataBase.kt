package com.example.m2tmdbapp2027.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (
    entities = [SocialBarEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TmdbDataBase : RoomDatabase() {

    abstract fun socialBarDao (): SocialBarDao

    companion object {
        private val DB_NAME = "tmdpapp.db"
        private var instance: TmdbDataBase? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    TmdbDataBase::class.java,
                    DB_NAME
                ).build().also {
                    instance = it
                }
            }
    }
}