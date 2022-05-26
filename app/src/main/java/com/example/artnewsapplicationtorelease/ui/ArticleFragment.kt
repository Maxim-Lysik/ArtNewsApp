package com.example.artnewsapplicationtorelease.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.artnewsapplicationtorelease.ArtNewsActivity
import com.example.artnewsapplicationtorelease.R

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ArtNewsActivity).viewModel
    }


}