package com.nghiamy.musicplayer.ui.main_screen

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var presenter: IMainContract.Presenter
    @Inject lateinit var factory:ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewmodel = ViewModelProviders.of(this, factory)[MainViewModel::class.java]
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
