package com.padcmyanmar.smtz.themovieapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.smtz.themovieapp.R
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.smtz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.smtz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.mvp.presenters.MovieDetailPresenter
import com.padcmyanmar.smtz.themovieapp.mvp.presenters.MovieDetailPresenterImpl
import com.padcmyanmar.smtz.themovieapp.mvp.views.MovieDetailView
import com.padcmyanmar.smtz.themovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.smtz.themovieapp.viewPods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(), MovieDetailView {

    companion object {

        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorsViewPod: ActorListViewPod

    private lateinit var mPresenter : MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setUpPresenter()

        setUpViewPod()
        setUpListener()

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let {
            mPresenter.onUiReadyForMovieDetail(this, it)
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MovieDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieDetail)
        tvDetailMovieName.text = movie.title ?: ""
        tvDetailMovieReleaseYear.text = movie.releaseDate?.substring(0, 4)
        tvRating.text = movie.voteAverage.toString() ?: ""
        rbDetailMovieRating.rating = movie.getRatingBasedOnFiveStars()
        movie.voteCount?.let {
            tvNumberOfVotes.text = "$it VOTES"
        }

        bindGenres(movie, movie.genres ?: listOf())

        tvOverView.text = movie.overview ?: ""
        tvOriginalTitle.text = movie.originalTitle ?: ""
        tvType.text = movie.getGenreAsCommaSeparatedString()
        tvProduction.text = movie.getProductionCountriesAsCommaSeparatedString()
        tvPremiere.text = movie.releaseDate ?: ""
        tvDescription.text = movie.overview ?: ""
    }

    private fun bindGenres(movie: MovieVO, genres: List<GenreVO>) {
        movie.genres?.count()?.let {
            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""              // genres[1]
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""

            if (it < 3) {
                tvThirdGenre.visibility = View.GONE
            } else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }
        }
    }

    private fun setUpViewPod() {
        actorsViewPod = vpActors as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        creatorsViewPod = vpCreators as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }

    private fun setUpListener(){
        btnBack.setOnClickListener{
            mPresenter.onTapBack()
        }
    }

    override fun showMovieDetails(movie: MovieVO) {
        bindData(movie)
    }

    override fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>) {
        actorsViewPod.setData(cast)
        creatorsViewPod.setData(crew)
    }

    override fun navigateBack() {
        finish()
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_SHORT).show()
    }
}