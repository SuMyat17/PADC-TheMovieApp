package com.padcmyanmar.smtz.themovieapp.mvp.presenters

import com.padcmyanmar.smtz.themovieapp.delegate.BannerViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.delegate.MovieViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.delegate.ShowcaseViewHolderDelegate
import com.padcmyanmar.smtz.themovieapp.mvp.views.MainView

interface MainPresenter : IBasePresenter, BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate {
    fun initView(view: MainView)
    fun onTapGenre(genrePosition: Int)
}