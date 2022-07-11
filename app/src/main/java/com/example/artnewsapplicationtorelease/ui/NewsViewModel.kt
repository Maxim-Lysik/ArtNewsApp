package com.example.artnewsapplicationtorelease.ui

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.example.artnewsapplicationtorelease.DataPoint
import com.example.artnewsapplicationtorelease.NewsApplication
import com.example.artnewsapplicationtorelease.models.Article
import com.example.artnewsapplicationtorelease.models.DayData
import com.example.artnewsapplicationtorelease.models.NewsResponse
import com.example.artnewsapplicationtorelease.repository.NewsRepository
import com.example.artnewsapplicationtorelease.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.util.*

class NewsViewModel(
    app: Application,
    val newsRepository: NewsRepository) : AndroidViewModel(app) {

    val searchedNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsResponse? = null





    init {
        getBreakingNews("skateboarding", breakingNewsPage, "en", "free-news.p.rapidapi.com", "d1565c3530msh540aa5917d83d32p15f952jsn233e528b8ff7")

        //getBreakingNews("art & graffiti", breakingNewsPage, "free-news.p.rapidapi.com", "d1565c3530msh540aa5917d83d32p15f952jsn233e528b8ff7")
    }

    fun getBreakingNews(q: String, page: Int,  lang: String, host: String, api: String) = viewModelScope.launch {

        safeBreakingNewsCall(q, breakingNewsPage, "en", host, api)

        /*searchedNews.postValue(Resource.Loading())
        val response = newsRepository.getSearchedNews(q, breakingNewsPage, host, api)
        searchedNews.postValue(handleBreakingNewsResponse(response))*/

    }


    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingNewsPage++
                if(breakingNewsResponse == null){
                    breakingNewsResponse = resultResponse
                } else{
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)

                }
                return Resource.Success(breakingNewsResponse?:resultResponse)
            }
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

    fun getArticleByLink(link:String): LiveData<Article> {
        return newsRepository.getArticleNeeded(link)
    }

    // FUNCTIONS FOR DAYDATA


    fun putDate(dayData: DayData) = viewModelScope.launch {
        newsRepository.upsert(dayData)
    }

    fun getAllDays() = newsRepository.getAllDays()

    fun deleteDay(dayData: DayData) = viewModelScope.launch {
        newsRepository.deleteDay(dayData)
    }


    fun getdayDataByID(id: Int): LiveData<DayData>{
        return newsRepository.getDayItemById(id)
    }

    /*fun itemByGeo(geoCoordinates: GeoCoordinates): LiveData<ArtItem?> {
        return retroRepository.getArtItem(geoCoordinates)
    }*/


    //









    private suspend fun safeBreakingNewsCall(q: String, page: Int, lang: String, host: String, api: String){
        searchedNews.postValue(Resource.Loading())
        try{
            if(hasInternetConnection()){

                val pages_number = 0
                val response = newsRepository.getSearchedNews(q, breakingNewsPage, "en", host, api)  // может быть тута
                val pages_number_again = response.body()!!.total_pages
                Log.d(TAG, "TOTAL NUMBER IS ${pages_number_again}");

                searchedNews.postValue(handleBreakingNewsResponse(response))
            }else{
                searchedNews.postValue(Resource.Error("No internet connection"))
            }


        } catch (t: Throwable){
                when(t){

                    is IOException -> searchedNews.postValue(Resource.Error("Network Failure"))
                    else -> searchedNews.postValue(Resource.Error(""))


                }


        }


    }








    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<NewsApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false


                }


            }
        }
        return false

    }


    // FOR LINEGRAPH















}