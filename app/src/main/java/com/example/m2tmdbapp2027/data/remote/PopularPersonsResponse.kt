package com.example.m2tmdbapp2027.data.remote

import com.google.gson.annotations.SerializedName


data class PopularPersonsResponse (

  @SerializedName("page"          ) var page         : Int,
  @SerializedName("results"       ) var results      : ArrayList<Person> = arrayListOf(),
  @SerializedName("total_pages"   ) var totalPages   : Int,
  @SerializedName("total_results" ) var totalResults : Int

)