package com.satish.nytimesmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.satish.nytimesmvvm.view_model.ArticleViewModel
import com.satish.nytimesmvvm.view_model.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var articleViewModel: ArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nyTimesApp = application as NyTimesApp
        nyTimesApp.networkComponent.injectMainActivity(this)
        setContentView(R.layout.activity_main)
        articleViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ArticleViewModel::class.java)
    }
}
