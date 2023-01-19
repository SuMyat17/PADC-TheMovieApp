package com.padcmyanmar.smtz.themovieapp.routers

import android.app.Activity
import android.content.Intent
import com.padcmyanmar.smtz.themovieapp.activities.MovieDetailsActivity
import com.padcmyanmar.smtz.themovieapp.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailsActivity(movieId: Int){
    startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
}

fun Activity.navigateToMovieSearchActivity(){
    val intent = Intent(this, MovieSearchActivity::class.java)
    startActivity(intent)
}