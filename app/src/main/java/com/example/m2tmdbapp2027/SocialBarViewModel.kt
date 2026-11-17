package com.example.m2tmdbapp2027

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.m2tmdbapp2027.data.db.SocialBarDao
import kotlinx.coroutines.launch

class SocialBarViewModel(private val socialBarDao: SocialBarDao) : ViewModel() {

    val nbLikes: LiveData<MutableMap<Int,Int>> = socialBarDao.getAllLikes().asLiveData()
    val isFavorite: LiveData<MutableMap<Int,Boolean>> = socialBarDao.getAllFavorites().asLiveData()

}