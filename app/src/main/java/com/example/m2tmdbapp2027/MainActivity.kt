package com.example.m2tmdbapp2027

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m2tmdbapp2027.data.remote.ITmdbApi
import com.example.m2tmdbapp2027.data.remote.Person
import com.example.m2tmdbapp2027.data.remote.RetrofitClient
import com.example.m2tmdbapp2027.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val LOGTAG= MainActivity::class.simpleName

    private lateinit var binding: ActivityMainBinding
    private lateinit var personAdapter: PersonAdapter
    private var persons = arrayListOf<Person>()

    private var totalPage = Int.MAX_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Init data binding
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Init total result text view with a default value
        binding.totalResultsText = getString(R.string.total_results_default_text)

        // Init recycler view
        binding.popularPersonRv.setHasFixedSize(true)
        binding.popularPersonRv.layoutManager = LinearLayoutManager(this)
        personAdapter = PersonAdapter()
        binding.popularPersonRv.adapter = personAdapter

        val api = RetrofitClient.create(ITmdbApi::class.java)
        lifecycleScope.launch {
            val response = api.getPopularPersons()
            totalPage = response.totalPages
            persons.addAll(response.results)
            binding.totalResultsText = getString(R.string.total_results_text,persons.size, response.totalResults)
            Log.d(LOGTAG, "Popular persons: ${response.results.size}")
        }
    }
}