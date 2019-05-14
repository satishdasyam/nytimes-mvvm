package com.satish.nytimesmvvm.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article_table")
data class Article (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Expose
    @SerializedName("title")
    var title: String = "",
    @Expose
    @SerializedName("abstract")
    var abstract: String = "",
    @Expose
    @SerializedName("thumbnail_standard")
    var thumbnailUrl: String = ""
)