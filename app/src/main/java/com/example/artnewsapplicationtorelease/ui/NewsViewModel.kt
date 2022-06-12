package com.example.artnewsapplicationtorelease.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artnewsapplicationtorelease.models.Article
import com.example.artnewsapplicationtorelease.models.NewsResponse
import com.example.artnewsapplicationtorelease.repository.NewsRepository
import com.example.artnewsapplicationtorelease.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    val searchedNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage = 1

    init {
        getBreakingNews("art & graffiti", "free-news.p.rapidapi.com", "d1565c3530msh540aa5917d83d32p15f952jsn233e528b8ff7")
    }

    fun getBreakingNews(q: String, host: String, api: String) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        val response = newsRepository.getSearchedNews(q, breakingNewsPage, host, api)
        searchedNews.postValue(handleBreakingNewsResponse(response))

    }


    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse -> return Resource.Success(resultResponse) }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }


}