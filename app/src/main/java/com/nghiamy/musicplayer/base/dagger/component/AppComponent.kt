package com.nghiamy.musicplayer.base.dagger.component

import com.nghiamy.musicplayer.base.MyApplication
import com.nghiamy.musicplayer.base.dagger.module.ActivityModule
import com.nghiamy.musicplayer.base.dagger.module.AppModule
import com.nghiamy.musicplayer.base.dagger.module.FragmentModule
import com.nghiamy.musicplayer.base.dagger.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component (modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class, FragmentModule::class,
                        ViewModelModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)
}