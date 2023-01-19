package com.padcmyanmar.smtz.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themovieapp.R
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.delegate.BannerViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.viewHolders.BannerViewHolder

class BannerAdapter(private val mDelegate: BannerViewHolderDelegate) : RecyclerView.Adapter<BannerViewHolder>() {

    private var mMovieList : List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_banner,parent,false)
        return BannerViewHolder(itemView,mDelegate)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return if(mMovieList.count()>5)
            5
        else
            mMovieList.count()
    }

    fun setNewData(movieList: List<MovieVO>) {

        this.mMovieList = movieList
        notifyDataSetChanged()
    }
}