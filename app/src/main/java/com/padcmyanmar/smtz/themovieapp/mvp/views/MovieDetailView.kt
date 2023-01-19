package com.padcmyanmar.smtz.themovieapp.mvp.views

import com.padcmyanmar.smtz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO

interface MovieDetailView : BaseView{
    fun showMovieDetails(movie: MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
}