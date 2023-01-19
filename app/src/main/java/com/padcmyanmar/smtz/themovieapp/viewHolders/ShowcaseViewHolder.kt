package com.padcmyanmar.smtz.themovieapp.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.delegate.ShowcaseViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_banner.view.*
import kotlinx.android.synthetic.main.view_holder_showcases.view.*

class ShowcaseViewHolder(itemView: View, private val mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO : MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let { movie ->
                mDelegate.onTapMovieFromShowcase(movie.id)
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovieVO = movie

        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.ivShowcase)

        itemView.tvshowcasesMovieName.text = movie.title
        itemView.tvShowcasesMovieDate.text = movie.releaseDate
    }
}