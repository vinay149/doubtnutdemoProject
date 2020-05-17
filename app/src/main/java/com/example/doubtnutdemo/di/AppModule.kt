package com.example.doubtnutdemo.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.doubtnutdemo.data.NewsListRepository
import com.example.doubtnutdemo.data.NewsListRepositoryImpl
import com.example.doubtnutdemo.db.NewsListDataBase
import com.example.doubtnutdemo.db.NewsDao
import com.example.doubtnutdemo.helper.RxBus
import com.example.doubtnutdemo.helper.RxBusImpl
import com.example.doubtnutdemo.service.NewsListService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [HomeModule::class])
open class AppModule {

    @Singleton
    @Provides
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://newsapi.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequestsPerHost = 10;
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)

            .dispatcher(dispatcher)
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun providesEventBus():RxBus{
        return RxBusImpl()
    }


    @Singleton
    @Provides
    fun provideViewModelFactory( repository: NewsListRepository): ViewModelProvider.Factory {
        return  ViewModelFactory(repository)
    }

    @Singleton
    @Provides
    fun provideGithubIssueService(retrofit:Retrofit):NewsListService{
        return retrofit.create(NewsListService::class.java)
    }


    @Singleton
    @Provides
    fun providesRepository(dao: NewsDao, service:NewsListService):NewsListRepository{
        return NewsListRepositoryImpl(dao, service)
    }

    @Singleton
    @Provides
    fun providesDao(dataBase: NewsListDataBase):NewsDao{
        return dataBase.userListDao()
    }


    @Singleton
    @Provides
    fun providesPdfDataBase(context: Context): NewsListDataBase {
        return Room.databaseBuilder(
            context, NewsListDataBase::class.java, "news_list_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

}