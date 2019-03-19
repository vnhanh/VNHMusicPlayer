package com.nghiamy.musicplayer.ui.all_songs.recyclerview

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.common.Constant.Companion.TAG
import com.nghiamy.musicplayer.base.datasource.song.SongRepository
import com.nghiamy.musicplayer.base.model.Song
import com.nghiamy.musicplayer.base.usecase.SongUtil
import com.nghiamy.musicplayer.ui.all_songs.EditMetadataSongDialogFragment
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.view_holder_all_songs.view.*

class AllSongViewHolder(val view:View, val repository: SongRepository) : RecyclerView.ViewHolder(view), PopupMenu.OnMenuItemClickListener {
    var song:Song?=null

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_play -> {
                Log.d(TAG, javaClass.name + " | menu item play music")
                playMusic()
            }

            R.id.menu_edit_song_metadata -> {
                showConfirmDialogEditMetadataSong()
            }

            R.id.menu_delete -> {

            }
        }
        return false
    }

    @SuppressLint("ResourceType")
    private fun showConfirmDialogEditMetadataSong() {
        if(view.context is AppCompatActivity){
            val activity = view.context as AppCompatActivity
            val spFragmentManager = activity.supportFragmentManager

            val tag = EditMetadataSongDialogFragment.getTag()
            val ft = spFragmentManager.beginTransaction()
            val prevDialogFrag = spFragmentManager.findFragmentByTag(tag)

            if(prevDialogFrag!=null){
                ft.remove(prevDialogFrag)
            }
            ft.commit()

            val newDialogFrag = EditMetadataSongDialogFragment.getInstance(song)
            newDialogFrag.onActionListener = onConfirmMetadataEdittedListener

            newDialogFrag.show(spFragmentManager, EditMetadataSongDialogFragment.getTag())
        }
    }

    private fun editSongMetadata() {
        SongUtil.editMetadataSong(song)
    }

    private fun playMusic() {
        song?.also { song ->
            song.path?.also { path ->
                Log.d(TAG, "\t\t\t | start play task... ${view.context}")
                SongUtil.playMusicOnBackground(view.context, song)
            }
        }
    }

    init {
        val popupMenu = PopupMenu(view.context, view.ic_context_menu)
        popupMenu.menuInflater.inflate(R.menu.menu_context_all_songs_item, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(this)

        view.ic_context_menu.setOnClickListener {
            popupMenu.show()
        }
        view.setOnClickListener { playMusic() }
    }

    fun bind(item: Song) {
        song = item

        item.picBytes?.also { byteArray ->
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            view.img_avatar_music.setImageBitmap(bitmap)
        }

        if(item.picBytes == null){
            view.img_avatar_music.setImageResource(R.drawable.ic_avatar_music_64)
        }

        view.tv_name_music.text = item.title?:""
        view.tv_description_music.text = item.artist?:""
    }

    private val onConfirmMetadataEdittedListener = object : EditMetadataSongDialogFragment.OnActionListener{
        override fun onConfirm(title: String, artist: String, author: String, album: String, genres: String) {
            song?.also { song ->
                song.title = title
                song.artist = artist
                song.author = author
                song.albumn = album
                song.genres = genres

                repository.updateSong(song)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object: Observer<Boolean> {
                        override fun onComplete() {

                        }

                        override fun onSubscribe(d: Disposable) {
                            Log.d(TAG, javaClass.name + " | updateSong: ${d}")
                        }

                        override fun onNext(item: Boolean) {
                            Log.d(TAG, javaClass.name + " | updateSong | onNext: ${item}")
                        }

                        override fun onError(e: Throwable) {
                            Log.d(TAG, javaClass.name + " | updateSong | onError: ${e}")
                        }
                    })
            }
        }
    }
}