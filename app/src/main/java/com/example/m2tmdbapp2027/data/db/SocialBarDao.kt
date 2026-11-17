package com.example.m2tmdbapp2027.data.db

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SocialBarDao {
    @Query("SELECT person_id, is_favorite FROM social_bar_table")
    fun getAllFavorites() : Flow<MutableMap<
            @MapColumn("person_id") Int,
            @MapColumn("is_favorite") Boolean>>

    @Query("SELECT person_id, nb_likes FROM social_bar_table")
    fun getAllLikes(): Flow<MutableMap<
            @MapColumn("person_id") Int,
            @MapColumn("nb_likes")Int>>

    @Upsert
    suspend fun upsert(entity: SocialBarEntity)
}