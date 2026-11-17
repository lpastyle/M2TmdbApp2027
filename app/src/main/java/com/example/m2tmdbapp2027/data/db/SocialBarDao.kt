package com.example.m2tmdbapp2027.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SocialBarDao {
    @Query("SELECT person_id, is_favorite FROM social_bar_table")
    fun getAllFavorites() : MutableMap<Int, Boolean>

    @Query("SELECT person_id, nb_likes FROM social_bar_table")
    fun getAllLikes(): MutableMap<Int, Int>

    @Upsert
    suspend fun upsert(entity: SocialBarEntity)
}