package com.example.doubtnutdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.doubtnutdemo.data.NewsListRepository
import com.example.doubtnutdemo.model.NewsListEntity
import com.example.doubtnutdemo.service.NewsListService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class NewsListViewModel(
    val repository: NewsListRepository
) : ViewModel() {

    @Inject
    lateinit var issueService: NewsListService
    private val pageSize = 30
    private val compositeDisposable = CompositeDisposable()
    var newsList: LiveData<PagedList<NewsListEntity>>? = null
    val newsTitle: MutableLiveData<String> = MutableLiveData()
    val newsName: MutableLiveData<String> = MutableLiveData()
    val author: MutableLiveData<String> = MutableLiveData()
    val imageUrl: MutableLiveData<String> = MutableLiveData()
    val content: MutableLiveData<String> = MutableLiveData()
    val link: MutableLiveData<String> = MutableLiveData()
    val openUrl: MutableLiveData<String> = MutableLiveData()

    init {
        val pagingConfig = PagedList.Config.Builder()
            .setPageSize(30)
            .setInitialLoadSizeHint(pageSize * 3)
            .setEnablePlaceholders(true)
            .build()
        newsList = LivePagedListBuilder<Int, NewsListEntity>(
            repository.fetchNews(),
            pagingConfig
        ).build()
    }

    fun getLatestNews(): LiveData<PagedList<NewsListEntity>>? {
        return newsList
    }

    fun syncData() {
        repository.syncIssueList()
    }

    fun openUrl(url: String) {
        openUrl.value = url
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun updateDetailsPageData(newsDetails: NewsDetails) {
        newsName.value = newsDetails.newsName
        author.value = newsDetails.authorName
        imageUrl.value = newsDetails.imageUrl
        link.value = newsDetails.link
        content.value = newsDetails.content
    }

}