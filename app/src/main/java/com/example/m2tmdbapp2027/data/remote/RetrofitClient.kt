package com.example.m2tmdbapp2027.data.remote

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    /* The API key is provided at build time by adding a property in the
       android/defaultConfig section of build.gradle.kts(:app) file.
       A secrets.properties file shall be created in project root directory
       with a line TMDB_API_KEY=<key-value-without-double-quotes>

    buildConfigField(
        "String",
        "TMDB_API_KEY",
        "\"${secrets.getProperty("TMDB_API_KEY", "")}\""
    )
    */

}