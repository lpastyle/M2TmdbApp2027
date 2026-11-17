package com.example.m2tmdbapp2027.data.db

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SocialBarDao {
    @Query("SELECT person_id, is_favorite FROM social_bar_table")
    fun getAllFavorites() : MutableMap<
            @MapColumn("person_id") Int,
            @MapColumn("is_favorite")Boolean>

    @Query("SELECT person_id, nb_likes FROM social_bar_table")
    fun getAllLikes(): MutableMap<
            @MapColumn("person_id") Int,
            @MapColumn("nb_likes")Boolean>

    @Upsert
    suspend fun upsert(entity: SocialBarEntity)
}