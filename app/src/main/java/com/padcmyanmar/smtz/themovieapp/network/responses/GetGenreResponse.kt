package com.padcmyanmar.smtz.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themovieapp.data.vos.GenreVO

data class GetGenreResponse(
    @SerializedName("genres")
    val genres : List<GenreVO>?
) {
}