package com.example.artnewsapplicationtorelease.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.artnewsapplicationtorelease.ArtNewsActivity
import com.example.artnewsapplicationtorelease.R
import com.example.artnewsapplicationtorelease.ui.notifications.StatsFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ArtNewsActivity).viewModel


        val args: ArticleFragmentArgs by navArgs()



       /* webView.setWebViewClient(object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                when (errorCode) {
                    ERROR_CONNECT -> {
                        Log.d(TAG, "кнопка ОК");}
                    ERROR_REDIRECT_LOOP -> {Log.d(TAG, "кнопка ОК");}
                    ERROR_UNKNOWN -> {Log.d(TAG, "кнопка ОК");}
                    ERROR_TOO_MANY_REQUESTS -> {Log.d(TAG, "кнопка ОК");}
                }
            }
        })
*/





        val article = args.article
        webView.apply {
            val settings = webView.settings
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()

            loadUrl(article.link)
        }





        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article was saved", Snackbar.LENGTH_SHORT).show()
        }


        val bundle = Bundle()
        val fragment: Fragment = StatsFragment()
        bundle.putString("company", "companyName")
        fragment.arguments = bundle

        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article was saved", Snackbar.LENGTH_SHORT).show()
        }
    }


}
