package com.padcmyanmar.smtz.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ActorVO(
    @SerializedName("adult")
    val adult : Boolean?,
    @SerializedName("gender")
    val gender : Int?,
    @SerializedName("id")
    val id : Int?,
    @SerializedName("known_for")
    val knownFor : List<MovieVO>?,
    @SerializedName("known_for_department")
    val knownForDeparment : String?,
    @SerializedName("name")
    val name : String?,
    @SerializedName("poularity")
    val popularity : Double?,
    @SerializedName("profile_path")
    val profilePath : String?,

    @SerializedName("original_name")
    val originalName : String?,
    @SerializedName("cast_id")
    val castId : Int?,
    @SerializedName("character")
    val character : String?,
    @SerializedName("credit_id")
    val creditId : String?,
    @SerializedName("order")
    val order : Int?,

    @SerializedName("department")
    val department : String?,
    @SerializedName("job")
    val job : String?,

)