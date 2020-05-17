package com.example.doubtnutdemo

import android.app.Application
import com.example.doubtnutdemo.di.AppComponent
import com.example.doubtnutdemo.di.DaggerAppComponent

open class DoubtNutDemoApplication : Application(){


    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }


    companion object {
        lateinit var instance: DoubtNutDemoApplication
    }

}