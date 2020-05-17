package com.example.doubtnutdemo

import androidx.annotation.NonNull
import com.example.doubtnutdemo.model.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface FakeNewsService {
    @NonNull
    @GET("/v2/top-headlines?country=us&apiKey=3d25623102aa4c94be05bf294b36e296")
    fun getGithubIssueList(): Observable<NewsResponse>
}