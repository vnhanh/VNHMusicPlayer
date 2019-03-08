package com.nghiamy.musicplayer.base.datasource.song

import com.nghiamy.musicplayer.base.model.Song
import com.nghiamy.musicplayer.base.usecase.SongUtil

class SongStorageSource {
    fun updateSongMetadata(song: Song) {
        SongUtil.editBasicSongMetadata(song)
    }
}