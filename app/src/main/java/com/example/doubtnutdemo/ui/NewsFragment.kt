package com.example.doubtnutdemo.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doubtnutdemo.DoubtNutDemoApplication
import com.example.doubtnutdemo.R
import com.example.doubtnutdemo.databinding.ActivityMainBinding
import com.example.doubtnutdemo.di.AppComponent
import com.example.doubtnutdemo.ui.comment.DetailsPageFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class NewsFragment:Fragment(){

    lateinit var homePageComponent: AppComponent
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel:NewsListViewModel
    lateinit var adapter:NewsListAdapter
    lateinit var binding:ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var clickHandler: ClickHandler


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        viewModel.syncData()
        viewModel.getLatestNews()?.observe(this, Observer {
            it?.let { value->
                adapter.submitList(value)
                Log.d("LatestIssue", "data${it}")
            }
        })
        viewModel.openUrl.observe(this, Observer {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            context?.let {
                startActivity(browserIntent)
            }
        })

        DoubtNutDemoApplication.instance.appComponent.rxBus().listen(ClickedHandlerOfNewsItem::class.java)
            .subscribe {
                it.value?.let {
                    navigateToDetailsPage(it)
                }
            }
    }

    override fun onAttach(context: Context) {
        homePageComponent = ( DoubtNutDemoApplication.instance).appComponent
        homePageComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)
        super.onAttach(context)
    }

    private fun navigateToDetailsPage(newsDetails: NewsDetails){
        viewModel.updateDetailsPageData(newsDetails)
        val details = DetailsPageFragment(viewModel)
        val newsFragment = NewsFragment()
        val fragmentManager = getFragmentManager()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.
            replace(R.id.fragment_container,details)
            transaction?.addToBackStack("details_fragment")
            transaction?.commit()
    }

    override fun onResume() {
        super.onResume()
    }


    private fun init(){
        recyclerView = rv_issue_list
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsListAdapter()
        recyclerView.adapter = adapter
    }

}