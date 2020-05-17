package com.example.doubtnutdemo.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_list")
class NewsListEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "author")
    var author: String? = null

    @ColumnInfo(name = "imageUrl")
    var imageUrl:String? = null

    @ColumnInfo(name = "content")
    var content:String?=null

    @ColumnInfo(name = "link")
    var link:String? = null

    @ColumnInfo(name="name")
    var newsChannelname:String?=null

}

