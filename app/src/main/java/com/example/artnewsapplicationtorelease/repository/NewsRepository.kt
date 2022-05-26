package com.example.artnewsapplicationtorelease.repository

import com.example.artnewsapplicationtorelease.api.RetrofitInstance
import com.example.artnewsapplicationtorelease.db.NewsDataBase

class NewsRepository(
    val db: NewsDataBase
) {

    suspend fun getSearchedNews(q: String, page: Int, host: String, api: String) = RetrofitInstance.api.getSearchedNews(q,page,host, api)


}