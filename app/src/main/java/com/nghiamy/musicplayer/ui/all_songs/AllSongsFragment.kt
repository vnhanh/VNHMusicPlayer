package com.nghiamy.musicplayer.ui.all_songs


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.model.Song
import com.nghiamy.musicplayer.base.ui.BaseFragment
import com.nghiamy.musicplayer.ui.all_songs.recyclerview.AllSongAdapter
import kotlinx.android.synthetic.main.fragment_all_songs.view.*
import javax.inject.Inject


class AllSongsFragment : BaseFragment(), IAllSongsContract.View {
    @Inject lateinit var presenter:IAllSongsContract.Presenter
    private lateinit var adapter:AllSongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = AllSongAdapter()
//        val song1 = Song().apply {
//            name = "Ben Thuong Hai"
//            artist = "Nguyen Hung - Nhu Quynh"
//            author = "Unknown"
//        }
//
//        val song2 = Song().apply {
//            name = "Ben song cho"
//            artist = "Manh Quynh"
//            author = "Che Lan Vien"
//        }
//
//        adapter.list.add(song1)
//        adapter.list.add(song2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_all_songs, container, false)

        setupRecyclerView(view)

        return view
    }

    override fun onResume() {
        super.onResume()

        presenter.onResume()
    }

    private fun setupRecyclerView(view: View) {
        view.recyclerview_all_musics.adapter = adapter
        view.recyclerview_all_musics.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.recyclerview_all_musics.layoutManager = layoutManager
    }

    /**
     * IAllSongsContract.View Implement
     */

    override fun showProgress(message: String) {
        view?.progress_bar?.visibility = View.VISIBLE
        view?.recyclerview_all_musics?.visibility = View.INVISIBLE
    }

    @SuppressLint("ResourceType")
    override fun showProgress(@IdRes msgRes: Int) {
        showProgress(getString(msgRes))
    }

    override fun hideProgress() {
        view?.progress_bar?.visibility = View.INVISIBLE
        view?.recyclerview_all_musics?.visibility = View.VISIBLE
    }

    override fun onDisplayAllMusics(musicList: ArrayList<Song>) {
        adapter.setListMusic(musicList)
    }

    /**
     * ------------------------
     */
}