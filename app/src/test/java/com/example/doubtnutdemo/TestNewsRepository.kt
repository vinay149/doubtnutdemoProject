package com.example.doubtnutdemo

import com.example.doubtnutdemo.data.NewsListRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class TestNewsRepository{

    lateinit var component: TestComponent
    lateinit var newsRepository: NewsListRepository

    @Before
    fun setUp(){
          newsRepository = mock(NewsListRepository::class.java)
    }
    @Test
    fun testDataBase(){

    }
}