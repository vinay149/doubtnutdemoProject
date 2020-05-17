package com.example.doubtnutdemo

import android.content.Context
import com.example.doubtnutdemo.data.NewsListRepository
import com.example.doubtnutdemo.di.AppComponent
import com.example.doubtnutdemo.di.AppModule
import com.example.doubtnutdemo.di.AppSubcomponents
import com.example.doubtnutdemo.di.HomePageComponent
import com.example.doubtnutdemo.di.comment.CommentComponent
import com.example.doubtnutdemo.helper.RxBus
import com.example.doubtnutdemo.service.NewsListService
import com.example.doubtnutdemo.ui.MainActivity
import com.example.doubtnutdemo.ui.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class])
interface TestComponent{
    fun inject(serviceDaggerTest: ServiceDaggerTest)
    fun getService():NewsListService
    fun getRepository():NewsListRepository

}