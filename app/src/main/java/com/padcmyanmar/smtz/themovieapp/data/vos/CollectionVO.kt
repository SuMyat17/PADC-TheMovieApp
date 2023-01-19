package com.padcmyanmar.smtz.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class CollectionVO (
    @SerializedName("id")
    val id : Int?,
    @SerializedName("name")
    val name : String?,
    @SerializedName("poster_path")
    val posterPath : String?,
    @SerializedName("backdrop_path")
    val backdropPath : String?


)