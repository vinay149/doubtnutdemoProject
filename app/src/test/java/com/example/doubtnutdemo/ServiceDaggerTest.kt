package com.example.doubtnutdemo

import com.example.doubtnutdemo.data.NewsListRepository
import com.example.doubtnutdemo.model.NewsResponse
import com.example.doubtnutdemo.service.NewsListService
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import javax.inject.Inject


class ServiceDaggerTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Inject
    lateinit var service:NewsListService

    lateinit var component:TestComponent

    @Before
    fun setUp(){
        component = DaggerTestComponent.create()
        component.inject(this)
        service = component.getService()
    }

    @Test
    fun newsListServiceTest(){
         val testNewsListObserver:TestObserver<NewsResponse> = TestObserver()
         val observer = service.getGithubIssueList()
         testNewsListObserver.assertNotSubscribed()
         observer.subscribe(testNewsListObserver)
         testNewsListObserver.assertSubscribed()

    }
}