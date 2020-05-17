package com.example.doubtnutdemo.model

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


data class News(
val source : Source,
val author : String,
val title : String,
val description : String,
val url : String,
val urlToImage : String,
val publishedAt : String,
val content : String)

@BindingAdapter("profileImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Log.d("dataHere","${imageUrl}")
    Glide.with(view.getContext())
        .load(imageUrl)
        .into(view)
}