package com.padcmyanmar.smtz.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.smtz.themovieapp.mvp.views.MovieDetailView

interface MovieDetailPresenter : IBasePresenter {
    fun initView(view: MovieDetailView)
    fun onUiReadyForMovieDetail(owner: LifecycleOwner, movieId: Int)
    fun onTapBack()
}