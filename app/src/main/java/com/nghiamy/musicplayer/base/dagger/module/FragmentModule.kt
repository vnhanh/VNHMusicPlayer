package com.nghiamy.musicplayer.base.dagger.module

import com.nghiamy.musicplayer.base.dagger.scope.PerFragment
import com.nghiamy.musicplayer.ui.all_songs.AllSongsFragment
import com.nghiamy.musicplayer.ui.all_songs.AllSongsModule
import com.nghiamy.musicplayer.ui.folders.MusicFoldersFragment
import com.nghiamy.musicplayer.ui.folders.MusicFoldersModule
import com.nghiamy.musicplayer.ui.playlist.PlaylistFragment
import com.nghiamy.musicplayer.ui.playlist.PlaylistModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector(modules = [AllSongsModule::class])
    @PerFragment
    internal abstract fun contributeAllSongsFragment() : AllSongsFragment

    @ContributesAndroidInjector(modules = [PlaylistModule::class])
    @PerFragment
    internal abstract fun contributePlaylistFragment() : PlaylistFragment

    @ContributesAndroidInjector(modules = [MusicFoldersModule::class])
    @PerFragment
    internal abstract fun contributeMusicFoldersFragment() : MusicFoldersFragment
}