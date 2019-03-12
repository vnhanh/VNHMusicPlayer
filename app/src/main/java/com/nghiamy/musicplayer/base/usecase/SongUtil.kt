package com.nghiamy.musicplayer.base.usecase

import android.content.Context
import android.content.Intent
import android.support.v4.media.MediaMetadataCompat
import android.util.Log
import com.nghiamy.musicplayer.base.common.Constant.Companion.TAG
import com.nghiamy.musicplayer.base.model.Song
import com.nghiamy.musicplayer.base.service.Key
import com.nghiamy.musicplayer.base.service.PlayMusicService

object SongUtil {
    fun playMusicOnBackground(context:Context, song:Song){
        val intent = Intent(context, PlayMusicService::class.java)
        intent.putExtra(Key.PATH, song.path)
        context.startService(intent)
    }

    fun editMetadataSong(song: Song?) : Boolean{
        Log.d(TAG, javaClass.name + " | editMetadataSong: ${song?.title}")

        if(song == null)
            return false

        val metaData = MediaMetadataCompat.Builder()

        metaData.putString(MediaMetadataCompat.METADATA_KEY_TITLE, song.title)
        metaData.putString(MediaMetadataCompat.METADATA_KEY_AUTHOR, song.author)
        metaData.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, song.artist)
        metaData.putString(MediaMetadataCompat.METADATA_KEY_GENRE, song.genres)

        return true
    }
}