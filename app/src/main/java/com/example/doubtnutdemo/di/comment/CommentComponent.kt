package com.example.doubtnutdemo.di.comment

import com.example.doubtnutdemo.di.FragmentScope
import com.example.doubtnutdemo.ui.comment.DetailsPageFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CommentModule::class])
interface CommentComponent {

    fun inject(activity: DetailsPageFragment)
    @Subcomponent.Factory
    interface Factory {
        fun create(): CommentComponent
    }
}
