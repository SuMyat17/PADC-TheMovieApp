package com.padcmyanmar.smtz.themovieapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.smtz.themovieapp.R
import com.padcmyanmar.smtz.themovieapp.adapters.BannerAdapter
import com.padcmyanmar.smtz.themovieapp.adapters.ShowcaseAdapter
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModel
import com.padcmyanmar.smtz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.smtz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themovieapp.delegate.BannerViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.delegate.MovieViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.delegate.ShowcaseViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.viewPods.ActorListViewPod
import com.padcmyanmar.smtz.themovieapp.viewPods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityOld : AppCompatActivity(), BannerViewHolderDelegate, ShowcaseViewHolderDelegate,
    MovieViewHolderDelegate {

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMovieViewPod: MovieListViewPod
    lateinit var mGenreMovieViewPod: MovieListViewPod

    //Model
    private val mMovieModel: MovieModel = MovieModelImpl

    //data
    private var mGenres: List<GenreVO>? = null

    //Module12 ep11
    lateinit var mActorListViewPod: ActorListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolBar()
        setUpBanner()
        //setUpGenreTabLayout()
        setUpListeners()
        setUpShowCase()
        setUpViewPods()
        requestData()
    }

    private fun requestData() {
        //Now Playing Movies
        mMovieModel.getNowPlayingMovies(
            onFailure = { showError(it) }
        )?.observe(this) {
            mBannerAdapter.setNewData(it)
        }

        //Popular Movies
        mMovieModel.getPopularMovies(
            onFailure = { showError(it) }
        )?.observe(this) {
            mBestPopularMovieViewPod.setData(it)
        }

        //Top Rated Movies /Showcases
        mMovieModel.getTopRatedMovies(
            onFailure = { showError(it) }
        )?.observe(this) {
            mShowcaseAdapter.setNewData(it)
        }

        //Genres
        mMovieModel.getGenres(
            onSuccess = {
                mGenres = it
                setUpGenreTabLayout(it)

//              Get Movies for the first Genre
                it.firstOrNull()?.id?.let { genreId ->                                                         // it.firstOrNull()?.id -> data type is 'optional Integer data type'
                    getMoviesByGenres(genreId)
                }
            },
            onFailure = {
                showError(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                mActorListViewPod.setData(it)
            },
            onFailure = {
                /// error message
                showError(it)
            }
        )
    }

    private fun getMoviesByGenres(genreId: Int) {                                //VL mr tok Int, DL nk NL mr tok String
        mMovieModel.getMoviesByGenre(genreId = genreId.toString(),
            onSuccess = {
                mGenreMovieViewPod.setData(it)
            },
            onFailure = {
                showError(it)
            }
        )
    }

    //1  ViewPod instances
    private fun setUpViewPods() {
        mBestPopularMovieViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieViewPod.setUpMovieListViewPod(this)

        mGenreMovieViewPod = vpGenreMovieList as MovieListViewPod
        mGenreMovieViewPod.setUpMovieListViewPod(this)

        //Module12 ep11
        mActorListViewPod = vpActorsHomeScreen as ActorListViewPod
    }

    private fun setUpShowCase() {
        mShowcaseAdapter = ShowcaseAdapter(this)
        rvShowcases.adapter = mShowcaseAdapter
        rvShowcases.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpListeners() {

        //Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mGenres?.getOrNull(tab?.position ?: 0)?.id?.let {
                    getMoviesByGenres(it)
                }
//                Snackbar.make(window.decorView,tab?.text?:"", Snackbar.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    //Tab Bar
    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {

//        dummyGenreList.forEach{
//            val tab = tabLayoutGenre.newTab()
//            tab.text = it
//            tabLayoutGenre.addTab(tab)
//        }

        mGenres?.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }
    }

    //Banner Adapter
    private fun setUpBanner() {

        mBannerAdapter = BannerAdapter(this)
        viewPagerBanner.adapter = mBannerAdapter

        //Dots Indicator
        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    //Search icon
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.movie_search -> {
                val intent = Intent(this, MovieSearchActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //App Bar Leading icon
    private fun setUpToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onTapMovieFormBanner(movieId: Int) {
        Snackbar.make(window.decorView, movieId.toString(), Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        Snackbar.make(window.decorView, movieId.toString(), Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }

    override fun onTapMovie(movieId: Int) {
        Snackbar.make(window.decorView, movieId.toString(), Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }

    //error
    private fun showError(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT).show()
    }
}