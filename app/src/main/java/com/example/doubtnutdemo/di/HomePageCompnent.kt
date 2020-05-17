package com.example.doubtnutdemo.di

import com.example.doubtnutdemo.ui.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [HomeModule::class])
interface HomePageComponent {
    fun inject(activity: MainActivity)
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomePageComponent
    }
}