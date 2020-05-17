package com.example.doubtnutdemo.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.doubtnutdemo.model.NewsListEntity

@Dao
interface NewsDao {

    @Query("SELECT *FROM news_list")
    fun getAllNews():DataSource.Factory<Int, NewsListEntity>

    @Query("DELETE FROM news_list")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news:NewsListEntity)
}