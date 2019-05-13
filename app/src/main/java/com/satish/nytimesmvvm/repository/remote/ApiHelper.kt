package com.satish.nytimesmvvm.repository.remote

import com.satish.nytimesmvvm.pojo.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiHelper {
@GET("nyt/all.json?api-key=KXnAjeKj3y6bS8OknsQxer3tNaAGDXwA")
    fun fetchArticles():Single<ArticleResponse>
}