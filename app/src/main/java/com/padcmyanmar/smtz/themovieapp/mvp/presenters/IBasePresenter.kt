package com.padcmyanmar.smtz.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {
    fun onUiReady(owner: LifecycleOwner)                //Activity ka setup pee loh ui components ty setup pee yin Presenter ko status update pyaw tr
}