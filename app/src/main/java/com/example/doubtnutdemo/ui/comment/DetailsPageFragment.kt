package com.example.doubtnutdemo.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.doubtnutdemo.R
import com.example.doubtnutdemo.data.CommentRepositoryImpl
import com.example.doubtnutdemo.databinding.LayoutDetailsPageBinding
import com.example.doubtnutdemo.di.comment.CommentComponent
import com.example.doubtnutdemo.ui.NewsListViewModel
import javax.inject.Inject

class DetailsPageFragment(val newsListViewModel: NewsListViewModel):Fragment()
{

    @Inject
    lateinit var repositoryImpl: CommentRepositoryImpl

    lateinit var binding:LayoutDetailsPageBinding
    lateinit var adapter:CommentPageAdapter
    lateinit var component:CommentComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_details_page,container,false)
        adapter = CommentPageAdapter()
        val view = binding.root
        binding.newsDetailsData = newsListViewModel
        return view
    }
}