package com.example.m2tmdbapp2027

import androidx.lifecycle.ViewModel

class SocialBarViewModel : ViewModel() {
    var nbLikes = mutableMapOf<Int, Int>()
    var isFavorite = mutableMapOf<Int, Boolean>()
}