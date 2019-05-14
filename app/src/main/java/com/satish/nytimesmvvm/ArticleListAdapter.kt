package com.satish.nytimesmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satish.nytimesmvvm.databinding.ArticleItemBinding
import com.satish.nytimesmvvm.pojo.Article


class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

    private val dataList = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticleItemBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.articleBinding.article = dataList[position]
    }

    fun addArtistList(artistList: List<Article>) {
        dataList.addAll(artistList)
        notifyDataSetChanged()
    }

    fun clearData() {
        dataList.clear()
        notifyDataSetChanged()
    }

    class ArticleViewHolder(val articleBinding: ArticleItemBinding) : RecyclerView.ViewHolder(articleBinding.root)

}