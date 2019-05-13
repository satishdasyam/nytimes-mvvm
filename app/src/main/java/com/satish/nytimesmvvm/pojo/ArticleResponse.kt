package com.satish.nytimesmvvm.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleResponse {
    @Expose
    @SerializedName("status")
    var status: String = ""
    @Expose
    @SerializedName("num_results")
    var totalRecords: Int = 0
    @Expose
    @SerializedName("results")
    lateinit var articleList: List<Article>
}