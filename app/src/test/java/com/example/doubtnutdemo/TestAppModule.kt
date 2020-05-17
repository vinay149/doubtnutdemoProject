package com.example.doubtnutdemo

import android.content.Context
import com.example.doubtnutdemo.data.NewsListRepository
import com.example.doubtnutdemo.di.AppModule
import com.example.doubtnutdemo.service.NewsListService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class TestAppModule:AppModule(){

    @Provides
    fun provideContext():Context{
        return DoubtNutDemoApplication.instance.applicationContext
    }


}