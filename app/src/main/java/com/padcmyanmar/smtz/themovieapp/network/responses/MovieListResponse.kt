package com.padcmyanmar.smtz.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themovieapp.data.vos.DateVO
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO

data class MovieListResponse(
    @SerializedName("page")
    val page : Int?,

    @SerializedName("dates")
    val dates : DateVO?,

    @SerializedName("results")
    val results : List<MovieVO>?
)
