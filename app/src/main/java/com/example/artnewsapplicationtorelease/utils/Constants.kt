package com.example.artnewsapplicationtorelease.utils

import com.example.artnewsapplicationtorelease.BuildConfig

class Constants {
    companion object {

        const val BASE_URL = "https://free-news.p.rapidapi.com"
        const val API_KEY = BuildConfig.API_KEY
        const val QUERY_PAGE_SIZE = 25
    }
}