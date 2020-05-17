package com.example.doubtnutdemo.data

import android.util.Log
import com.example.doubtnutdemo.db.NewsDao
import com.example.doubtnutdemo.model.News
import com.example.doubtnutdemo.model.NewsListEntity
import com.example.doubtnutdemo.model.NewsResponse
import com.example.doubtnutdemo.service.NewsListService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class NewsListRepositoryImpl (val dao: NewsDao, val service:NewsListService): NewsListRepository {
    private val compositeDisposable = CompositeDisposable()
    var observable : Observable<NewsResponse> ?= null
    override fun getIssueListFromNetWork() {

    }

    override fun syncIssueList() {
        observable = service.getGithubIssueList()
        observable?.let { observable->
            compositeDisposable.add(
                observable
                    .subscribeOn(Schedulers.io())
                    .map { t->t.articles }
                    .flatMap { data -> Observable.fromIterable(data) }
                    .observeOn(Schedulers.io())
                    .subscribeWith(object : DisposableObserver<News>() {
                        override fun onComplete() {
                            Log.d("onComplete:::::","insertIssueInDataBase$")
                        }

                        override fun onNext(t: News) {
                            Log.d("InsertIt:::::","insertIssueInDataBase$${t.author}")
                            val newsListEntity = NewsListEntity()
                            newsListEntity.imageUrl = t.urlToImage
                            newsListEntity.content = t.description
                            newsListEntity.link = t.url
                            newsListEntity.title = t.title
                            newsListEntity.newsChannelname = t.publishedAt
                            newsListEntity.author = t.author
                            dao.insert(newsListEntity)
                        }
                        override fun onError(e: Throwable) {
                            Log.d("Error", "Some Error occurred${e}")
                        }
                    })
            )
        }
    }

    override fun fetchNews():androidx.paging.DataSource.Factory<Int,NewsListEntity> {
        return dao.getAllNews()
    }

    override fun clear() {
        compositeDisposable.clear()
    }

    override fun deleteOldData() {
        compositeDisposable.add(Completable
            .complete()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :DisposableCompletableObserver(){
                override fun onComplete() {
                    dao.deleteAll()
                }

                override fun onError(e: Throwable) {
                }

            }))
    }

}

