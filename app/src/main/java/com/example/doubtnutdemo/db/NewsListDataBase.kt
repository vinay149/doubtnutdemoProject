package com.example.doubtnutdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.doubtnutdemo.model.NewsListEntity

@Database(entities = [NewsListEntity::class] , version = 4 , exportSchema = false)
abstract class NewsListDataBase : RoomDatabase(){
  abstract fun userListDao():NewsDao
}

