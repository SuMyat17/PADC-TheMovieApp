package com.padcmyanmar.smtz.themovieapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.smtz.themovieapp.R
import com.padcmyanmar.smtz.themovieapp.adapters.BannerAdapter
import com.padcmyanmar.smtz.themovieapp.adapters.ShowcaseAdapter
import com.padcmyanmar.smtz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.smtz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.mvp.presenters.MainPresenter
import com.padcmyanmar.smtz.themovieapp.mvp.presenters.MainPresenterImpl
import com.padcmyanmar.smtz.themovieapp.mvp.views.MainView
import com.padcmyanmar.smtz.themovieapp.viewPods.ActorListViewPod
import com.padcmyanmar.smtz.themovieapp.viewPods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityMvp : AppCompatActivity(), MainView {

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter
    lateinit var mBestPopularMovieViewPod: MovieListViewPod
    lateinit var mGenreMovieViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()

        setUpToolBar()
        setUpBanner()
        setUpListeners()
        setUpShowCase()
        setUpViewPods()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(MainPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    //1  ViewPod instances
    private fun setUpViewPods() {
        mBestPopularMovieViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieViewPod.setUpMovieListViewPod(mPresenter)

        mGenreMovieViewPod = vpGenreMovieList as MovieListViewPod
        mGenreMovieViewPod.setUpMovieListViewPod(mPresenter)

        //Module12 ep11
        mActorListViewPod = vpActorsHomeScreen as ActorListViewPod
    }

    private fun setUpShowCase() {
        mShowcaseAdapter = ShowcaseAdapter(mPresenter)
        rvShowcases.adapter = mShowcaseAdapter
        rvShowcases.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpListeners() {

        //Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPresenter.onTapGenre(tab?.position ?: 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    //Banner Adapter
    private fun setUpBanner() {

        mBannerAdapter = BannerAdapter(mPresenter)
        viewPagerBanner.adapter = mBannerAdapter

        //Dots Indicator
        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    //App Bar Leading icon
    private fun setUpToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
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

    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMovieViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowcaseAdapter.setNewData(topRatedMovies)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        genreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }
    }

    override fun showMoviesByGenre(moviesByGenre: List<MovieVO>) {
        mGenreMovieViewPod.setData(moviesByGenre)
    }

    override fun showActors(actors: List<ActorVO>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailsScreen(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_SHORT).show()
    }
}