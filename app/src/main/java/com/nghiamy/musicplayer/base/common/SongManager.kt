package com.nghiamy.musicplayer.base.common

import android.content.Context
import android.util.Log
import java.io.File

class SongManager(val context: Context) {
    private val TAG = "LOG"
    private val songList = ArrayList<HashMap<String,String>>()
    private val MP3_PATTERN = ".mp3"
    private val SONG_TITLE = "SONG_TITLE"
    private val SONG_PATH = "SONG_PATH"

    private var ignoreDirs:MutableList<String> = mutableListOf(
        "^sdcard/Android$",
        "^storage/.+/Android$",
        "^storage/.+/LOST.DIR$",
        "/storage/.+/System Volume Information"
    )

    private fun scan(){
        val dirList = arrayOf("storage", "sdcard")
        for(dirName in dirList){
            val dir = File(dirName)
            scanDirectory(dir)
        }
    }

    private fun scanDirectory(dir:File){
        val path = dir.path
        if(ignoreDirs.size > 0){
            for (dirPath in ignoreDirs){
                if(dirPath.toRegex().matches(path)){
                    Log.d(TAG, "matched path: ${path}")
                    ignoreDirs.remove(dirPath)
                    return
                }
            }

        }
        dir.listFiles()?.also {files ->
            //            Log.d(TAG, "Number of files: ${files.size}")
            if(files.size > 0){
                for(file in files){
                    if(file.isDirectory){
                        scanDirectory(file)
                    }else{
                        addNewSong(file)
                    }
                }
            }
        }
    }

    private fun addNewSong(file:File){
        val songMap = HashMap<String,String>()
        if(file.name.endsWith(MP3_PATTERN)){
            songMap.put(SONG_TITLE, file.name.substring(0, file.name.length-4))
            songMap.put(SONG_PATH, file.absolutePath)
            songList.add(songMap)
        }
    }

    fun getPlaylist() : ArrayList<HashMap<String,String>> {
        scan()
        return songList
    }
}