package com.padcmyanmar.smtz.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.smtz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO

class MovieDetailViewModel : ViewModel() {

    private var mMovieModel : MovieModel = MovieModelImpl

    var movieDetailsLiveData : LiveData<MovieVO?>? = null
    var castLiveData = MutableLiveData<List<ActorVO>>()
    var crewLiveData = MutableLiveData<List<ActorVO>>()
    var mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(movieId: Int){

        movieDetailsLiveData = mMovieModel.getMovieDetails(movieId = movieId.toString(), onFailure = { mErrorLiveData.postValue(it) } )

        mMovieModel.getCreditsByMovies(
            movieId = movieId.toString(),
            onSuccess = {
                castLiveData.postValue(it.first ?: listOf())
                crewLiveData.postValue(it.second ?: listOf())
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }
}