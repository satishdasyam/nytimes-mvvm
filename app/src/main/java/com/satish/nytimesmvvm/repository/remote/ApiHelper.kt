package com.satish.nytimesmvvm.repository.remote

import com.satish.nytimesmvvm.pojo.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET


/**There are two rate limits per API: 4,000 requests per day and
10 requests per minute. You should sleep 6 seconds between calls to
avoid hitting the per minute rate limit.*/

interface ApiHelper {
    @GET("nyt/all.json?api-key=KXnAjeKj3y6bS8OknsQxer3tNaAGDXwA")
    fun fetchArticles(): Single<ArticleResponse>
}