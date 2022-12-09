package com.example.artnewsapplicationtorelease.repository

import androidx.lifecycle.LiveData
import com.example.artnewsapplicationtorelease.api.RetrofitInstance
import com.example.artnewsapplicationtorelease.db.NewsDataBase
import com.example.artnewsapplicationtorelease.models.Article
import com.example.artnewsapplicationtorelease.models.DayData

class NewsRepository(
    val db: NewsDataBase
) {
    suspend fun getSearchedNews(q: String, page: Int, lang: String, host: String, api: String) = RetrofitInstance.api.getSearchedNews(q,page, lang, host, api)

    suspend fun upsert(article: Article) = db.getItemDao().upsert(article)

    fun getSavedNews() = db.getItemDao().getAllArticles()

    fun getArticleNeeded(link: String):LiveData<Article> = db.getItemDao().getByLink(link)

    suspend fun deleteArticle(article: Article) = db.getItemDao().deleteArticle(article)

    // Functions for DauData table //

    suspend fun upsert(dayData: DayData) = db.getDaydataDao().upsert(dayData)

    fun getAllDays() = db.getDaydataDao().getAllDataObjects()

    fun getDayItemById(id: Int): LiveData<DayData> = db.getDaydataDao().getDayDataItem(id)

    suspend fun deleteDay(dayData: DayData) = db.getDaydataDao().deleteDateObject(dayData)

}