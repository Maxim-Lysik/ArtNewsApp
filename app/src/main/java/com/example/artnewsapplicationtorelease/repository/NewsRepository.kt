package com.example.artnewsapplicationtorelease.repository

import com.example.artnewsapplicationtorelease.api.RetrofitInstance
import com.example.artnewsapplicationtorelease.db.NewsDataBase
import com.example.artnewsapplicationtorelease.models.Article

class NewsRepository(
    val db: NewsDataBase
) {

    suspend fun getSearchedNews(q: String, page: Int, host: String, api: String) = RetrofitInstance.api.getSearchedNews(q,page,host, api)

    suspend fun upsert(article: Article) = db.getItemDao().upsert(article)

    fun getSavedNews() = db.getItemDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getItemDao().deleteArticle(article)

    // test
}