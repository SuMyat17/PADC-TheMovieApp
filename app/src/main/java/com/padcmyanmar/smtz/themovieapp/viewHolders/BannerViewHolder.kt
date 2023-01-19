package com.padcmyanmar.smtz.themovieapp.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.delegate.BannerViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_banner.view.*

class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovie : MovieVO? = null

    init {
        itemView.setOnClickListener{
            mMovie?.let { movie ->
                mDelegate.onTapMovieFormBanner(movie.id)
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovie = movie

        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.ivBannerImage)

        itemView.tvBannerMovieName.text = movie.title
    }
}