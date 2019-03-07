package com.nghiamy.musicplayer.base.common

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioAttributes.CONTENT_TYPE_MUSIC
import android.media.AudioManager
import android.media.AudioTrack
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.support.annotation.IdRes
import android.util.Log
import com.nghiamy.musicplayer.base.common.Constant.Companion.TAG

object Util{

    @SuppressLint("ResourceType")
    fun getString(context:Context?, @IdRes id:Int) : String{
        if(context == null)
            return ""

        return context.getString(id)
    }

    fun isEmpty(str:String?):Boolean{
        return str == null || "".equals(str)
    }

    fun isNotEmpty(str:String?) : Boolean {
        return !isEmpty(str)
    }
}