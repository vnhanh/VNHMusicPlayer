package com.nghiamy.musicplayer.ui.all_songs


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.ui.BaseFragment


class AllSongsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_all_songs, container, false)
    }

}
