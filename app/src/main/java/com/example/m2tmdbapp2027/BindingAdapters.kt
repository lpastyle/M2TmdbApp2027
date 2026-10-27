package com.example.m2tmdbapp2027

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

// URL got from https://api.themoviedb.org/3/configuration?api_key=<TMDB_API_KEY>
private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185"

@BindingAdapter("imageUrl")
fun ImageView.loadImage(profilePath: String?) {
    if (profilePath.isNullOrEmpty()) {
        Picasso.get().cancelRequest(this)
        setImageResource(R.drawable.question_mark)
        return
    }
    // Placeholder drawable were downloaded from https://fonts.google.com/icons
    Picasso.get()
        .load(IMAGE_BASE_URL + profilePath)
        .placeholder(R.drawable.hourglass_empty)
        .error(R.drawable.question_mark)
        .into(this)
}
