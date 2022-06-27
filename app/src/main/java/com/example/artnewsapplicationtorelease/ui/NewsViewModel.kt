package com.example.artnewsapplicationtorelease.ui

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        getBreakingNews("art & graffiti", breakingNewsPage, "free-news.p.rapidapi.com", "d1565c3530msh540aa5917d83d32p15f952jsn233e528b8ff7")
    }

    fun getBreakingNews(q: String, page: Int, host: String, api: String) = viewModelScope.launch {

        safeBreakingNewsCall(q, breakingNewsPage, host, api)

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

    // FUNCTIONS FOR DAYDATA


    fun putDate(dayData: DayData) = viewModelScope.launch {
        newsRepository.upsert(dayData)
    }

    fun getAllDays() = newsRepository.getAllDays()

    fun deleteDay(dayData: DayData) = viewModelScope.launch {
        newsRepository.deleteDay(dayData)
    }


    //









    private suspend fun safeBreakingNewsCall(q: String, page: Int, host: String, api: String){
        searchedNews.postValue(Resource.Loading())
        try{
            if(hasInternetConnection()){

                val pages_number = 0
                val response = newsRepository.getSearchedNews(q, breakingNewsPage, host, api)  // может быть тута
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


  //  val list2: List<DataPoint> = listOf(DataPoint(4,5),DataPoint(4,5), DataPoint(4,5), DataPoint(4,5), DataPoint(4,5))

    /*fun generateDataPoints():List<DataPoint>{

        val random = Random()
        return (0..20).map {
            DataPoint(it, random.nextInt(50)+1)
        }


    }
*/


}