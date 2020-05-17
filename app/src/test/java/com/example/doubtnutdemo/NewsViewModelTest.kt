package com.example.doubtnutdemo

import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.example.doubtnutdemo.data.NewsListRepository
import com.example.doubtnutdemo.di.DaggerAppComponent
import com.example.doubtnutdemo.service.NewsListService
import com.example.doubtnutdemo.ui.NewsListViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import javax.inject.Inject


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NewsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    val repository:NewsListRepository?=null
    @Inject
    lateinit var newsService:NewsListService
    @Mock
    var lifecycleOwner: LifecycleOwner? = null

    private var newsViewModel:NewsListViewModel?=null
    lateinit var component:TestComponent

    @Before
    fun setUp(){
        component = DaggerTestComponent.create()

        repository?.let {
            newsViewModel = NewsListViewModel(repository)
        }
    }

    @Test
    fun testDataFromApi() {
        newsViewModel?.syncData()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

}
