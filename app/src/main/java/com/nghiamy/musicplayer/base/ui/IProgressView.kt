package com.nghiamy.musicplayer.base.ui

import android.support.annotation.IdRes

interface IProgressView {
    fun showProgress(@IdRes msgRes:Int)
    fun showProgress(message:String)
    fun hideProgress()
}