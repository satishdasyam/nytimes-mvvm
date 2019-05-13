package com.satish.nytimesmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.satish.nytimesmvvm.databinding.ActivityMainBinding
import com.satish.nytimesmvvm.pojo.Article
import com.satish.nytimesmvvm.view_model.ArticleViewModel
import com.satish.nytimesmvvm.view_model.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articleListAdapter: ArticleListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nyTimesApp = application as NyTimesApp
        nyTimesApp.networkComponent.injectMainActivity(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        articleViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ArticleViewModel::class.java)
        setRecyclerView()
        setObservers()
    }

    private fun setRecyclerView() {
        val linearManger = LinearLayoutManager(this).apply {
            orientation = RecyclerView.VERTICAL
        }
        articleListAdapter = ArticleListAdapter()
        binding.rvArticleList.apply {
            layoutManager = linearManger
            adapter = articleListAdapter
        }
    }

    private fun setObservers() {
        articleViewModel.getArticleList()?.observe(this,
            Observer<List<Article>> { t -> t?.let { articleListAdapter.addArtistList(it) } })
    }
}
