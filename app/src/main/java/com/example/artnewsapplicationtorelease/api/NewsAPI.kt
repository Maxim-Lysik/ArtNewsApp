package com.example.artnewsapplicationtorelease.api

import com.example.artnewsapplicationtorelease.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v1/search")
    suspend fun getSearchedNews(
        @Query("q")
        query: String = "skateboarding",
        @Query("page") page: Int = 1,
        @Query("lang") lang: String = "en",
        @Header("X-RapidAPI-Host") host: String,
        @Header("X-RapidAPI-Key") Apikey: String

    ): Response<NewsResponse>

}


// test2