package com.example.doubtnutdemo.data

import androidx.lifecycle.MutableLiveData
import com.example.doubtnutdemo.ui.comment.Comment

interface CommentRepository {
    fun clear()
    fun fetchCommentFromNetwork(issue:Long)
    fun getCommentListById():MutableLiveData<List<Comment>>?
}
