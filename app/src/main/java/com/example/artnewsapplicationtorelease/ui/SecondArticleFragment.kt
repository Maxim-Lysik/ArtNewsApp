package com.example.artnewsapplicationtorelease.ui

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.artnewsapplicationtorelease.ArtNewsActivity
import com.example.artnewsapplicationtorelease.R
import com.example.artnewsapplicationtorelease.models.Article
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.fragment_article.webView
import kotlinx.android.synthetic.main.fragment_second_article.*
import kotlinx.android.synthetic.main.fragment_stats.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


lateinit var viewModel: NewsViewModel

class SecondArticleFragment : Fragment(R.layout.fragment_second_article) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ArtNewsActivity).viewModel


        val sharedPreference =  this.activity!!.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val gson = Gson()
        val json: String? = sharedPreference.getString("Our_object", "DEFAULT")

        var obj: Article = Article("2", 2.0, "sdsa", "clean_url", "country", true, "language", "link", "media", "published_date", "published_date_precision"
            , 2, "rights", "summary", "title", "topic", "twitter_account")

        if(json =="DEFAULT"){
            obj= Article("2", 2.0, "sdsa", "clean_url", "country", true, "language", "link", "media", "published_date", "published_date_precision"
                , 2, "rights", "summary", "title", "topic", "twitter_account")

        }
        else{obj= gson.fromJson(json, Article::class.java)}

        webView.apply {
            webViewClient = WebViewClient()



           loadUrl(obj.link)
        }




    }
}