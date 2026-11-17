package com.example.m2tmdbapp2027.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("social_bar_table")
class SocialBarEntity {
    @PrimaryKey
    @ColumnInfo(name = "person_id")
    val personId: Int = 0

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false

    @ColumnInfo(name = "nb_likes")
    val nbLikes: Int = 0
}