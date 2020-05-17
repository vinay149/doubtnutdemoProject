package com.example.doubtnutdemo.di

import com.example.doubtnutdemo.di.comment.CommentComponent
import dagger.Module

@Module(subcomponents = [HomePageComponent::class,CommentComponent::class])
class AppSubcomponents