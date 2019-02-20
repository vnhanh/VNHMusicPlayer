package com.nghiamy.musicplayer.ui.main_screen

import android.os.Bundle
import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), IMainContract.View {

    @Inject lateinit var presenter: IMainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate()

        btn_showData.setOnClickListener {
            showData()
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun showData() {
        val songs = presenter.onGetAllSongs()
        for (song in songs){
            txt_result.text = String.format("Name: %s | Author: %s", song.name, song.author)
        }
    }

}
