package com.nghiamy.musicplayer.ui.all_songs


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_all_songs.*
import javax.inject.Inject


class AllSongsFragment : BaseFragment(), IAllSongsContract.View {

    @Inject lateinit var presenter:IAllSongsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_all_songs, container, false)
    }

    /**
     * IAllSongsContract.View
     *
     */

    override fun onShowProgressing() {
        prg_bar.visibility = View.VISIBLE
        recyclerview_all_musics.visibility = View.INVISIBLE
    }

    override fun onHideProgressing() {
        prg_bar.visibility = View.INVISIBLE
        recyclerview_all_musics.visibility = View.VISIBLE
    }

    override fun onDisplayAllMusics(musicList: ArrayList<HashMap<String, String>>) {

    }

}