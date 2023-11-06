package com.example.artnewsapplicationtorelease.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
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
    //val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ArtNewsActivity).viewModel



    val args: ArticleFragmentArgs by navArgs()


        val article = args.article
        webView.apply {

            val settings = webView.settings
            settings.javaScriptEnabled = true

            webViewClient = WebViewClient()
            loadUrl(article.link)
            //https://www.entrepreneur.com/article/368477    article.link txt txt txt txt txt5 txt7 test8
        }



       // var needed_object2 = viewModel.getArticleByLink(article.link!!)
      //  Log.d(ContentValues.TAG, "ICHO: ${needed_object2.value!!.title}")




        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article was saved", Snackbar.LENGTH_SHORT).show()
        }

        //val hui = args.shit
        //Log.d(ContentValues.TAG, "SUKAAA: ${hui}")



        /* val bundle = Bundle().apply { article
             //putSerializable("article", it)
             putString("article2", article.title.toString())
         }
 */

        val bundle = Bundle()
        val fragment: Fragment = StatsFragment()
        bundle.putString("company", "companyName")
        // bundle.
        //  bundle.putString("project", projectName)
        fragment.arguments = bundle

// test75
        /*val hui = args.shit
        Log.d(ContentValues.TAG, "SUKAAA: ${hui}")*/


        /*val fragment_stats = StatsFragment()
        fragment_stats.arguments = bundle*/

        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article was saved", Snackbar.LENGTH_SHORT).show()
        }
    }


}
