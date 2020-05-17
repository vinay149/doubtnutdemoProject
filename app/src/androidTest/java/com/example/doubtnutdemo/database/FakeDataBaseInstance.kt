package com.example.doubtnutdemo.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.doubtnutdemo.DoubtNutDemoApplication
import com.example.doubtnutdemo.db.NewsDao
import com.example.doubtnutdemo.db.NewsListDataBase
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FakeDataBaseInstance {

    companion object{
        var instanceOfDataBase:NewsDao?=null
        fun getInstance():NewsDao? {
            if (instanceOfDataBase == null) {
                instanceOfDataBase = Room.databaseBuilder(
                    ApplicationProvider.getApplicationContext<Context>(),
                    NewsListDataBase::class.java,
                    "news_list_database"
                ).fallbackToDestructiveMigration()
                    .build().userListDao()
            }
            return instanceOfDataBase
        }
    }
}