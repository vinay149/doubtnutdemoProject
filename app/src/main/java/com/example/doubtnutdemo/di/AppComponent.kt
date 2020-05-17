package com.example.doubtnutdemo.di

import android.content.Context
import com.example.doubtnutdemo.di.comment.CommentComponent
import com.example.doubtnutdemo.helper.RxBus
import com.example.doubtnutdemo.ui.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,AppSubcomponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(activity: NewsFragment)
    fun rxBus():RxBus
    fun commentComponent():CommentComponent.Factory
    fun homeComponent():HomePageComponent.Factory
}