package com.padcmyanmar.smtz.themovieapp

import android.app.Application
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModelImpl

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()

//        val mMovieModel : MovieModel = MovieModelImpl
        MovieModelImpl.initDatabase(applicationContext)
    }
}