package com.example.m2tmdbapp2027

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.m2tmdbapp2027.data.db.SocialBarDao

class SocialBarViewModel(private val socialBarDao: SocialBarDao) : ViewModel() {

    val nbLikes: LiveData<MutableMap<Int,Int>> = socialBarDao.getAllLikes().asLiveData()
    val isFavorite: LiveData<MutableMap<Int,Boolean>> = socialBarDao.getAllFavorites().asLiveData()

    class SocialBarViewModelFactory(private val socialBarDao: SocialBarDao) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(SocialBarViewModel::class.java)) {
                "Unknown ViewModel class ${modelClass.name}"
            }
            return SocialBarViewModel(socialBarDao) as T
        }
    }

}