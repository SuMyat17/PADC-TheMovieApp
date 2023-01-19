package com.padcmyanmar.smtz.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.smtz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.smtz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO

class MainViewModel : ViewModel() {

    private var mMovieModel : MovieModel = MovieModelImpl

    var nowPlayingMoviesLiveData: LiveData<List<MovieVO>>? = null
    var popularMoviesLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMoviesLiveData: LiveData<List<MovieVO>>? = null
    var genresLiveData = MutableLiveData<List<GenreVO>>()
    var moviesByGenreLiveData = MutableLiveData<List<MovieVO>>()
    var actorsLiveData = MutableLiveData<List<ActorVO>>()
    var mErrorLiveData = MutableLiveData<String>()

    fun getInitialData() {
        nowPlayingMoviesLiveData = mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMoviesLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }
        topRatedMoviesLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }

        mMovieModel.getGenres(
            onSuccess = {
                genresLiveData.postValue(it)
                getMoviesByGenre(0)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
        mMovieModel.getActors(
            onSuccess = {
                actorsLiveData.postValue(it)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }

    fun getMoviesByGenre(genrePosition: Int){
        genresLiveData.value?.getOrNull(genrePosition)?.id?.let {
            mMovieModel.getMoviesByGenre(
                it.toString(),
                onSuccess = { movies ->
                moviesByGenreLiveData.postValue(movies)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            })
        }
    }

}