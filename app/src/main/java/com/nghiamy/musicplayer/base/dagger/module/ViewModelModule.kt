package com.nghiamy.musicplayer.base.dagger.module

import androidx.lifecycle.ViewModelProvider
import com.nghiamy.musicplayer.base.dagger.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(PostListViewModel::class)
//    internal abstract fun postListViewModel(viewModel: PostListViewModel): ViewModel

    //Add more ViewModels here
}