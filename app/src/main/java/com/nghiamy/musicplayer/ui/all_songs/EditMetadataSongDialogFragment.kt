package com.nghiamy.musicplayer.ui.all_songs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.common.Constant.Companion.TAG
import com.nghiamy.musicplayer.base.model.Song
import kotlinx.android.synthetic.main.fragment_dialog_edit_metadata_song.*
import java.lang.ref.WeakReference

/**
 * Check if this dialog retains inputed data the last time
 */
class EditMetadataSongDialogFragment : DialogFragment() {
    companion object {
        fun getTag():String{
            return this.javaClass.name
        }

        private var INSTANCE:EditMetadataSongDialogFragment?=null

        fun getInstance(song:Song?):EditMetadataSongDialogFragment {
            if(INSTANCE == null){
                INSTANCE = EditMetadataSongDialogFragment()
            }

            song?.also { song ->
                val bundle = Bundle()
                bundle.putString(Song.KEY.ID.value, song.id)
                bundle.putString(Song.KEY.TITLE.value, song.title)
                bundle.putString(Song.KEY.ARTIST.value, song.artist)
                bundle.putString(Song.KEY.AUTHOR.value, song.author)
                bundle.putString(Song.KEY.ALBUMN.value, song.albumn)
                bundle.putString(Song.KEY.GENRES.value, song.genres)

                INSTANCE?.arguments = bundle
            }

            return INSTANCE!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, javaClass.name + "| onCreateView()")
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_dialog_edit_metadata_song, container, false)

        initDataShowed()
        setupClickListeners()

        return view
    }

    private fun initDataShowed() {
        arguments?.also { bundle ->
            val title = bundle.getString(Song.KEY.TITLE.value)
            val artist = bundle.getString(Song.KEY.ARTIST.value)
            val author = bundle.getString(Song.KEY.AUTHOR.value)
            val albumn = bundle.getString(Song.KEY.ALBUMN.value)
            val genres = bundle.getString(Song.KEY.GENRES.value)

            edt_song_name.setText(title)
            edt_artist.setText(artist)
            edt_author.setText(author)
            edt_album.setText(albumn)
            edt_genres.setText(genres)
        }
    }

    private fun setupClickListeners() {
        btn_cancel.setOnClickListener {
            this@EditMetadataSongDialogFragment.dismiss()
        }

        btn_confirm.setOnClickListener {
            val title = edt_song_name.text.toString()
            val artist = edt_artist.text.toString()
            val author = edt_author.text.toString()
            val album = edt_album.text.toString()
            val genres = edt_genres.text.toString()

            onActionListener?.onConfirm(title, artist, author, album, genres)
        }
    }

    private var weakListener:WeakReference<OnActionListener>?=null
    var onActionListener:OnActionListener?
            get() = this.weakListener?.get()
        set(value) {
            value?.also {
                weakListener = WeakReference(it)
            }
        }

    interface OnActionListener{
        fun onConfirm(title:String, artist:String, author:String, album:String, genres:String)
    }
}