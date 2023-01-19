package com.padcmyanmar.smtz.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.smtz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themovieapp.mvp.views.MainView

class MainPresenterMvpImpl : ViewModel(), MainPresenter {

    var mView: MainView? = null

    private var mMovieModel: MovieModel = MovieModelImpl

    private var mGenres: List<GenreVO> = listOf()

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

        mMovieModel.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showNowPlayingMovies(it)
        }

        mMovieModel.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showPopularMovies(it)
        }

        mMovieModel.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showTopRatedMovies(it)
        }

        mMovieModel.getGenres(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)

                it.firstOrNull()?.id?.let { firstGenreId ->
                    onTapGenre(firstGenreId)
                }
            },
            onFailure = {
                mView?.showError(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                mView?.showActors(it)
            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapGenre(genrePosition: Int) {
        mMovieModel.getMoviesByGenre(
            genreId = genrePosition.toString(),
            onSuccess = {
                mView?.showMoviesByGenre(it)
            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapMovieFormBanner(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }
}