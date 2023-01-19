package com.padcmyanmar.smtz.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.smtz.themovieapp.mvp.views.MovieDetailView

class MovieDetailPresenterImpl: ViewModel(), MovieDetailPresenter {

    private var mView : MovieDetailView? = null

    private var mMovieModel : MovieModel = MovieModelImpl

    override fun initView(view: MovieDetailView) {
        mView = view
    }

    override fun onUiReadyForMovieDetail(owner: LifecycleOwner, movieId: Int) {
        mMovieModel.getMovieDetails(
            movieId = movieId.toString(),
            onFailure = {
                mView?.showError(it)
            }
        )?.observe(owner){
            it?.let{
                mView?.showMovieDetails(it)
            }
        }

        mMovieModel.getCreditsByMovies(
            movieId = movieId.toString(),
            onSuccess = {
                mView?.showCreditsByMovie(cast = it.first, crew = it.second)
            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onUiReady(owner: LifecycleOwner) {}

    override fun onTapBack() {
        mView?.navigateBack()
    }

}