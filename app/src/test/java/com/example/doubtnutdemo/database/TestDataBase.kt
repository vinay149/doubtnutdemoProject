package com.example.doubtnutdemo.database

import com.example.doubtnutdemo.db.NewsDao
import com.example.doubtnutdemo.model.NewsListEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestDataBase {
    var newsDao: NewsDao?=null

    @Before
    fun setUp(){
        newsDao = FakeDataBaseInstance.getInstance()
    }

    @Test
    fun testInsertNewsDetailsInNewsTable(){
        val newsListEntity = NewsListEntity()
        newsListEntity.author = "vinay"
        newsListEntity.content = "news"
        newsListEntity.imageUrl= "www.image.com"
        newsListEntity.newsChannelname = "anything"
        newsListEntity.title = "sports news"
        newsDao?.insert(newsListEntity)
    }
}
