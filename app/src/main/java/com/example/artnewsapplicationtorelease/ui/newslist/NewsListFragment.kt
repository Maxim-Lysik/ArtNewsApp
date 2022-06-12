package com.example.artnewsapplicationtorelease.ui.newslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artnewsapplicationtorelease.ArtNewsActivity
import com.example.artnewsapplicationtorelease.R
import com.example.artnewsapplicationtorelease.adapters.NewsAdapter
import com.example.artnewsapplicationtorelease.api.NewsAPI
import com.example.artnewsapplicationtorelease.api.RetrofitInstance
import com.example.artnewsapplicationtorelease.databinding.FragmentNewsListBinding
import com.example.artnewsapplicationtorelease.repository.NewsRepository
import com.example.artnewsapplicationtorelease.ui.NewsViewModel
import com.example.artnewsapplicationtorelease.utils.Constants
import com.example.artnewsapplicationtorelease.utils.Resource
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "SearchedNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        val root: View = binding.root


       // val textView: TextView = binding.textHome
       /* homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/


     //   val buttonone: Button = binding.button1

        /*buttonone.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val repo: NewsRepository = NewsRepository()
                repo.getSearchedNews(
                    "art && graffiti",
                    1,
                    "free-news.p.rapidapi.com",
                    "d1565c3530msh540aa5917d83d32p15f952jsn233e528b8ff7"
                )
            }
        }
*/



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ArtNewsActivity).viewModel
        setupRecyclerView()



        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_navigation_news_to_articleFragment, bundle
            )
        }



        viewModel.searchedNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object: RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }

    }




    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }






   /*  fun GetAPISUKA(){
        val url = "https://free-news.p.rapidapi.com/v1/search?q=Elon%20Musk&lang=en"
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://free-news.p.rapidapi.com/v1/search?q=Elon%20Musk&lang=en")
            .get()
            .addHeader("X-RapidAPI-Host", "free-news.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "d1565c3530msh540aa5917d83d32p15f952jsn233e528b8ff7")
            .build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)
            }

        })

    }
*/




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

