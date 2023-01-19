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
import com.padcmyanmar.smtz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themovieapp.delegate.BannerViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.delegate.MovieViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.delegate.ShowcaseViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.mvvm.MainViewModel
import com.padcmyanmar.smtz.themovieapp.viewPods.ActorListViewPod
import com.padcmyanmar.smtz.themovieapp.viewPods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityMvvm : AppCompatActivity(), BannerViewHolderDelegate, ShowcaseViewHolderDelegate,
    MovieViewHolderDelegate {

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMovieViewPod: MovieListViewPod
    lateinit var mGenreMovieViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()

        setUpToolBar()
        setUpBanner()
        setUpListeners()
        setUpShowCase()
        setUpViewPods()

        observeLiveData()
    }

    private fun setUpViewModel() {
        mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mMainViewModel.getInitialData()
    }

    private fun observeLiveData() {
//        mMainViewModel.nowPlayingMoviesLiveData?.observe(this){
//            mBannerAdapter.setNewData(it)
//        }
        mMainViewModel.nowPlayingMoviesLiveData?.observe(this, mBannerAdapter::setNewData)
        mMainViewModel.popularMoviesLiveData?.observe(this, mBestPopularMovieViewPod::setData)
        mMainViewModel.topRatedMoviesLiveData?.observe(this, mShowcaseAdapter::setNewData)
        mMainViewModel.genresLiveData.observe(this, this::setUpGenreTabLayout)
        mMainViewModel.actorsLiveData.observe(this, mActorListViewPod::setData)
        mMainViewModel.moviesByGenreLiveData.observe(this, mGenreMovieViewPod::setData)
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
                mMainViewModel.getMoviesByGenre(tab?.position ?: 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    //Tab Bar
    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {

        genreList.forEach {
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
        if (item.itemId == R.id.movie_search) {
            val intent = Intent(this, MovieSearchActivity::class.java)
            startActivity(intent)
            return true
        }
        return false
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
}