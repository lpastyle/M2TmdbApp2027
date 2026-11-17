package com.example.m2tmdbapp2027

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.m2tmdbapp2027.databinding.ActivityPersonDetailBinding

const val PERSON_ID_EXTRA_KEY = "person_id_extra_key"

class PersonDetailActivity : AppCompatActivity() {
    private val LOGTAG = PersonDetailActivity::class.simpleName
    private lateinit var binding: ActivityPersonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retreive activity extras
        binding.personIdTv.text = intent.extras?.getInt(
            PERSON_ID_EXTRA_KEY,
            -1
        ).toString()

    }
}