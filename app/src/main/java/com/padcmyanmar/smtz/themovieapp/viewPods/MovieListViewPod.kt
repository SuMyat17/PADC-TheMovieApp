package com.padcmyanmar.smtz.themovieapp.viewPods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.smtz.themovieapp.adapters.MovieAdapter
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.delegate.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    lateinit var mMovieAdapter : MovieAdapter
    lateinit var mDelegate : MovieViewHolderDelegate

    override fun onFinishInflate() {
        //setUpMovieAdapter()
        super.onFinishInflate()
    }

    //Function to call from Activity
    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpMovieAdapter()
    }

    //Delegate setter
    private fun setDelegate(delegate: MovieViewHolderDelegate){
        this.mDelegate = delegate
    }

    //Movie Adapter
    private fun setUpMovieAdapter() {
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }

    fun setData(movieList: List<MovieVO>) {
        mMovieAdapter.setNewData(movieList)
    }
}