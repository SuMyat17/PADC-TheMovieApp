package com.padcmyanmar.smtz.themovieapp.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.delegate.MovieViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_banner.view.*
import kotlinx.android.synthetic.main.view_holder_movie_list.view.*

class MovieViewHolder(itemView: View, private val mDelegate: MovieViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mMovieVO : MovieVO? = null

    init{
        itemView.setOnClickListener {
            mMovieVO?.let { movie ->
                Snackbar.make(itemView, "Tapped", Snackbar.LENGTH_SHORT).show()
                mDelegate.onTapMovie(movie.id)
            }
        }
    }

    fun bindData(movie : MovieVO){
        mMovieVO = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMovieImage)

        itemView.tvMovieName.text = movie.title
        itemView.tvMovieRating.text = movie.voteAverage?.toString()
        itemView.rbMovieRating.rating = movie.getRatingBasedOnFiveStars()
    }
}