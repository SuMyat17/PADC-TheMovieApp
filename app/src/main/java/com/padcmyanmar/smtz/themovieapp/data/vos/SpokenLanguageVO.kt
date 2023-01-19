package com.padcmyanmar.smtz.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class SpokenLanguageVO(
    @SerializedName("english_name")
    val englishName : String?,
    @SerializedName("iso_639_1")
    val iso6391 : String?,
    @SerializedName("name")
    val name : String?
)
