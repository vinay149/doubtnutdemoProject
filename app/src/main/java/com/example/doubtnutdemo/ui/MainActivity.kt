package com.example.doubtnutdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.doubtnutdemo.R
import com.example.doubtnutdemo.di.AppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var homePageComponent: AppComponent
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var adapter:NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content)
        if(savedInstanceState==null) {
            init()
        }
    }

    private fun init(){
        val issueFragment = NewsFragment()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.
            add(R.id.fragment_container,issueFragment,"news_fragment").commit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        Log.d("gettingBackPressed","${supportFragmentManager.findFragmentByTag("news_fragment")}")
        super.onBackPressed()
    }


}
