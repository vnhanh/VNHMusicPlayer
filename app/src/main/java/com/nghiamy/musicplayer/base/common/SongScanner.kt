package com.nghiamy.musicplayer.base.common

import android.content.Context
import android.media.MediaMetadataRetriever
import android.util.Log
import com.nghiamy.musicplayer.base.model.Song
import java.io.File
import java.lang.Exception

class SongScanner(val context: Context) {
    private val TAG = "LOG"
    private val songList = ArrayList<Song>()
    private val MP3_PATTERN = ".mp3"

    private var ignoreDirs:MutableList<String> = mutableListOf(
        "^sdcard/Android$",
        "^storage/.+/Android$",
        "^storage/.+/LOST.DIR$",
        "/storage/.+/System Volume Information"
    )

    private fun scan(){
        Log.d(TAG, String.format("%s | scan...", javaClass.name))
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
                    Log.d(TAG, "${javaClass.name} | matched path: ${path}")
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
        if(file.name.endsWith(MP3_PATTERN)){
            val song = Song().also {

                it.path = file.absolutePath

                try {
                    val metadataRetriver = MediaMetadataRetriever()
                    metadataRetriver.setDataSource(it.path)
                    it.picBytes = metadataRetriver.embeddedPicture
                    it.name = metadataRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)

                    if(Util.isEmpty(it.name)){
                        it.name = file.name.substring(0, file.name.length-4)
                    }

                    it.artist = metadataRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
                    it.author = metadataRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR)
                    it.genres = metadataRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE)
                }
                catch (e:Exception){
                    Log.d(TAG, String.format("\t\t\taddNewSong | exception: ${e}"))
                }

                if(Util.isEmpty(it.artist))
                    it.artist = "Unknown Artist"
                if(Util.isEmpty(it.author))
                    it.author = "Unknown Author"
                if(Util.isEmpty(it.genres))
                    it.genres = "Unknown Genres"
            }
            songList.add(song)
        }
    }

    // need to save music list into database if didn't
    fun getPlaylist() : ArrayList<Song> {
        scan()
        return songList
    }
}