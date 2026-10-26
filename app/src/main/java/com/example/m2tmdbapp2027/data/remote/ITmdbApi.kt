package com.example.m2tmdbapp2027.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// https://api.themoviedb.org/3/person/popular?api_key=<TMDB_API_KEY>&page=1

interface ITmdbApi {
    @GET("person/popular")
    suspend fun getPopularPersons(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "fr-FR"
    ) : PopularPersonsResponse
}