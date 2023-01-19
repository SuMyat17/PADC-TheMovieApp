package com.padcmyanmar.smtz.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themovieapp.data.vos.ActorVO

data class GetCreditsByMoviesResponse (
    @SerializedName("cast")
    val cast : List<ActorVO>?,
    @SerializedName("crew")
    val crew : List<ActorVO>?,
    )