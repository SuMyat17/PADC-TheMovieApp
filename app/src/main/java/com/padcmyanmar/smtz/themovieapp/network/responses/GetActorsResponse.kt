package com.padcmyanmar.smtz.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themovieapp.data.vos.ActorVO

data class GetActorsResponse (
    @SerializedName("results")
    val results : List<ActorVO>?
        )