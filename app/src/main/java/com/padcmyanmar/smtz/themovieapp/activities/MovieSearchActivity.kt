package com.padcmyanmar.smtz.themovieapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.padcmyanmar.smtz.themovieapp.R
import com.padcmyanmar.smtz.themovieapp.adapters.MovieAdapter
import com.padcmyanmar.smtz.themovieapp.data.models.BaseModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.smtz.themovieapp.delegate.MovieViewHolderDelegate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_search.*
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(), MovieViewHolderDelegate {

    lateinit var mMovieAdapter : MovieAdapter

    private val mMovieModel : MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        setUpMovieAdapter()
        setUpListener()
    }

    private fun setUpListener() {
        etSearch.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap {
                mMovieModel.searchMovies(query = it.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mMovieAdapter.setNewData(it)
            }
    }

    private fun setUpMovieAdapter() {
        mMovieAdapter = MovieAdapter(this)
        rvMovies.adapter = mMovieAdapter
        rvMovies.layoutManager = GridLayoutManager(this,2)
//        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }

    override fun onTapMovie(movieId: Int) {
        Snackbar.make(window.decorView, movieId.toString(), Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }
}   