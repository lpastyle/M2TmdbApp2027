package com.example.m2tmdbapp2027

import android.app.Application
import com.example.m2tmdbapp2027.data.db.TmdbDataBase

class TmdbApplication : Application() {
    // Using by lazy so the database is only created when they're needed
    // rather than when the application starts
    val database by lazy {
        TmdbDataBase.getInstance(this)
    }
}
