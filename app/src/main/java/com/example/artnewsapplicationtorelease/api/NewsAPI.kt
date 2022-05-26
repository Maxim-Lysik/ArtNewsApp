package com.example.artnewsapplicationtorelease.api

import com.example.artnewsapplicationtorelease.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v1/search")
    suspend fun getSearchedNews(
        @Query("q")
        query: String,
        @Query ("page") page: Int = 1,
        @Query ("X-RapidAPI-Key") apiKey: String = API_KEY

    )

}