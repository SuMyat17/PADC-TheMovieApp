package com.padcmyanmar.smtz.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themovieapp.R
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.delegate.MovieViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.viewHolders.MovieViewHolder

class MovieAdapter(private val mDelegate: MovieViewHolderDelegate) : RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList : List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_list,parent,false)
        return MovieViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }
}