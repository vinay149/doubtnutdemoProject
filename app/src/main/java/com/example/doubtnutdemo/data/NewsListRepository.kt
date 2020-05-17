package com.example.doubtnutdemo.data

import com.example.doubtnutdemo.model.NewsListEntity

interface NewsListRepository {
    fun getIssueListFromNetWork()
    fun clear()
    fun syncIssueList()
    fun fetchNews():androidx.paging.DataSource.Factory<Int, NewsListEntity>
    fun deleteOldData()
}