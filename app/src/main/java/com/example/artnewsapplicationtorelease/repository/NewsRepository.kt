package com.example.artnewsapplicationtorelease.repository

import androidx.lifecycle.LiveData
import com.example.artnewsapplicationtorelease.api.RetrofitInstance
import com.example.artnewsapplicationtorelease.db.NewsDataBase
import com.example.artnewsapplicationtorelease.models.Article
import com.example.artnewsapplicationtorelease.models.DayData

class NewsRepository(
    val db: NewsDataBase
) {

    suspend fun getSearchedNews(q: String, page: Int, host: String, api: String) = RetrofitInstance.api.getSearchedNews(q,page,host, api)

    suspend fun upsert(article: Article) = db.getItemDao().upsert(article)

    fun getSavedNews() = db.getItemDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getItemDao().deleteArticle(article)

    // functions for DauData table

    suspend fun upsert(dayData: DayData) = db.getDaydataDao().upsert(dayData)

    fun getAllDays() = db.getDaydataDao().getAllDataObjects()

    fun getDayItemById(id: Int): LiveData<DayData> = db.getDaydataDao().getDayDataItem(id)



    /*fun getArtItem(geoCoordinates: GeoCoordinates): LiveData<ArtItem?> =
        retroDao.getArtItem(geoCoordinates)*/


    suspend fun deleteDay(dayData: DayData) = db.getDaydataDao().deleteDateObject(dayData)

}