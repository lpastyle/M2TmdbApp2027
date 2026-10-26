package com.example.m2tmdbapp2027

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.m2tmdbapp2027.data.remote.ITmdbApi
import com.example.m2tmdbapp2027.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val LOGTAG= MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val api = RetrofitClient.create(ITmdbApi::class.java)
        lifecycleScope.launch {
            val response = api.getPopularPersons()
            Log.d(LOGTAG, "Popular persons: ${response.results.size}")
        }
    }
}