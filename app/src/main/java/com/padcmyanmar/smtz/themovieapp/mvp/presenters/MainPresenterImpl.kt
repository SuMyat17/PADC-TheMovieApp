package com.padcmyanmar.smtz.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themovieapp.interactors.MovieInteractor
import com.padcmyanmar.smtz.themovieapp.interactors.MovieInteractorImpl
import com.padcmyanmar.smtz.themovieapp.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {

    var mView: MainView? = null

    private var mMovieInteractor: MovieInteractor = MovieInteractorImpl

    private var mGenres: List<GenreVO> = listOf()

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

        mMovieInteractor.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showNowPlayingMovies(it)
        }

        mMovieInteractor.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showPopularMovies(it)
        }

        mMovieInteractor.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showTopRatedMovies(it)
        }

        mMovieInteractor.getGenres(
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

        mMovieInteractor.getActors(
            onSuccess = {
                mView?.showActors(it)
            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapGenre(genrePosition: Int) {
        mMovieInteractor.getMoviesByGenre(
            genreId = mGenres.getOrNull(genrePosition)?.id.toString(),
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