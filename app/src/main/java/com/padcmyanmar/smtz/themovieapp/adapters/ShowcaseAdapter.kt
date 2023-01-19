package com.padcmyanmar.smtz.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themovieapp.R
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.delegate.ShowcaseViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.viewHolders.ShowcaseViewHolder

class ShowcaseAdapter(private val mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.Adapter<ShowcaseViewHolder>() {

    private var mMovieList : List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_showcases,parent,false)
        return ShowcaseViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }
}