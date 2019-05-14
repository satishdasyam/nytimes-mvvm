package com.satish.nytimesmvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.satish.nytimesmvvm.pojo.Article
import com.satish.nytimesmvvm.repository.NyRepository
import javax.inject.Inject

class ArticleViewModel @Inject
constructor(private val nyRepository: NyRepository) : ViewModel() {

    init {
        //nyRepository.fetchArticleMetadata()
        //nyRepository.getStreamingData()
    }

    fun getArticleList(): LiveData<MutableList<Article>>? {
        return nyRepository.articleList
    }

    override fun onCleared() {
        super.onCleared()
        nyRepository.onClear()
    }

    fun openConnection() {
        nyRepository.openSSEConnection()
    }

    fun closeConnection() {
        nyRepository.closeConnection()
    }
}