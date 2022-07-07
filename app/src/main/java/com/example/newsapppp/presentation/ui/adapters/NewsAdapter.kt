package com.example.newsapppp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapppp.R
import com.example.newsapppp.presentation.model.ArticleModel
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    var listNews = listOf<ArticleModel>()

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    private var onItemClickListener: ((ArticleModel) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = listNews[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvTitle.text = article.title
            author.text = article.author
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    fun setOnItemClickListener(listener: (ArticleModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    fun setList(list: List<ArticleModel>) {
        listNews = list
        notifyDataSetChanged()
    }
}
