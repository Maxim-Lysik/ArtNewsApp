package com.example.artnewsapplicationtorelease.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artnewsapplicationtorelease.R
import com.example.artnewsapplicationtorelease.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*





class UsersDiffCallBack(
    private val oldList: List<Article>,
    private val newList: List<Article>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.title == newUser.title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }

}



class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            Log.d(TAG, "CHECKING AAAAAAAAAAAAAAAA")


            Log.d(TAG, "PIZDETS FROM ${oldItem.title} AND ${newItem.title} EQUALS ${oldItem.title == newItem.title}")
            return oldItem.title.toString() == newItem.title.toString()


        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            Log.d(TAG, "CHECKING ${oldItem.title} AND ${newItem.title} IS: ${oldItem.title == newItem.title}")
            return oldItem == newItem

        }
    }
    val differ = AsyncListDiffer(this, differCallback)







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.media).into(ivArticleImage)
            tvSource.text = article.author
            tvTitle.text = article.title
            tvDescription.text = article.summary
            tvPublishedAt.text = article.published_date
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }




    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}


