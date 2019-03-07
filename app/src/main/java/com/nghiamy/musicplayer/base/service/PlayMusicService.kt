package com.nghiamy.musicplayer.base.service

import android.app.IntentService
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.util.Log
import com.nghiamy.musicplayer.base.common.Constant.Companion.TAG
import com.nghiamy.musicplayer.base.common.ConstantSharedPreference
import com.nghiamy.musicplayer.base.common.Util
import com.nghiamy.musicplayer.base.sharedpreferences.CustomizeSharedPreferences

class PlayMusicService() : IntentService("Play Music Service"), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Log.d(TAG, javaClass.name + " | MediaPlayer.OnErrorListener | exception")
        return false
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, javaClass.name + " | onHandleIntent: ${intent} | thread: ${Thread.currentThread().name}")

        val csp:CustomizeSharedPreferences = CustomizeSharedPreferences(this, ConstantSharedPreference.SHAREDPREF_NAME)

        var musicPath:String?=null

        if(intent != null){
            musicPath = intent.getStringExtra(Key.PATH)
        }
        else{
            musicPath = csp.getString(Key.PATH, "")
        }

        if(Util.isEmpty(musicPath))
            return

        csp.putString(Key.PATH, musicPath!!)

        val mp = MediaPlayer.create(this, Uri.parse(musicPath))
        if(Build.VERSION.SDK_INT >= 21){
            val attributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()

            mp.setAudioAttributes(attributes)
        }else{
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC)
        }
        mp.isLooping = true
        mp.start()
    }

    override fun onDestroy() {
        Log.d(TAG, javaClass.name + " | onDestroy")
        super.onDestroy()
    }
}