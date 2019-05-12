package com.satish.nytimesmvvm.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class ArticleMetadata {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1
    @SerializedName("title")
    var title: String = ""
    @SerializedName("abstract")
    var abstract: String = ""
    @SerializedName("thumbnail_standard")
    var thumbnailUrl: String = ""
}