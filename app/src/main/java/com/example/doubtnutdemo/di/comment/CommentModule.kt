package com.example.doubtnutdemo.di.comment

import com.example.doubtnutdemo.data.CommentRepositoryImpl
import com.example.doubtnutdemo.di.FragmentScope
import com.example.doubtnutdemo.service.CommentService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class CommentModule {


    @FragmentScope
    @Provides
    fun providesCommentRepository(service:CommentService):CommentRepositoryImpl{
        return CommentRepositoryImpl(service)
    }

    @FragmentScope
    @Provides
    fun providesCommentService(retrofit:Retrofit):CommentService{
        return retrofit.create(CommentService::class.java)
    }
}