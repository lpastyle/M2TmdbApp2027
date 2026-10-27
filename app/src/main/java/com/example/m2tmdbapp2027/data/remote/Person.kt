package com.example.m2tmdbapp2027.data.remote

import com.google.gson.annotations.SerializedName

data class Person (

  @SerializedName("adult"                ) var adult              : Boolean,
  @SerializedName("gender"               ) var gender             : Int,
  @SerializedName("id"                   ) var id                 : Int,
  @SerializedName("known_for_department" ) var knownForDepartment : String,
  @SerializedName("name"                 ) var name               : String,
  @SerializedName("original_name"        ) var originalName       : String,
  @SerializedName("popularity"           ) var popularity         : Float,
  @SerializedName("profile_path"         ) var profilePath        : String,
  @SerializedName("known_for"            ) var knownFor           : ArrayList<KnownFor> = arrayListOf()

)