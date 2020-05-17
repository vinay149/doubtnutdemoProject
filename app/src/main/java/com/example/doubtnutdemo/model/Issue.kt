package com.example.doubtnutdemo.model

data class IssueResponse(val listOfIssue:List<Issue>)
data class Issue (
    val title : String,
    val number:Long,
    val body : String
)