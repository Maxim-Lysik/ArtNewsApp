package com.example.artnewsapplicationtorelease.ui.newslist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artnewsapplicationtorelease.ArtNewsActivity
import com.example.artnewsapplicationtorelease.R
import com.example.artnewsapplicationtorelease.adapters.NewsAdapter
import com.example.artnewsapplicationtorelease.databinding.FragmentNewsListBinding
import com.example.artnewsapplicationtorelease.models.DayData
import com.example.artnewsapplicationtorelease.ui.NewsViewModel
import com.example.artnewsapplicationtorelease.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.example.artnewsapplicationtorelease.utils.Resource
import kotlinx.android.synthetic.main.fragment_news_list.*
import java.util.*

class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "SearchedNewsFragment"
   // var counter_start = 0




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



        // test5

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ArtNewsActivity).viewModel
        setupRecyclerView()

        val sharedPreference =  this.activity!!.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        sharedPreference.getString("username","defaultName")
        sharedPreference.getLong("l",1L)


      /*  val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)*/











        var counter_start: Int
        counter_start = sharedPreference.getInt("Ass", 0)


        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_navigation_news_to_articleFragment, bundle
            )

                   // IT WORKED
            counter_start = counter_start+1
            sharedPreference.edit().putInt("Ass", counter_start).commit()



        }



        viewModel.searchedNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->

                        // тута и будем работац

                       /* val count = newsResponse.total_pages
                        val ourlist = emptyList<Article>()
                        var i = 1
                        while (i <= count) {
                            viewModel.getBreakingNews("art & graffiti", i, "free-news.p.rapidapi.com", "d1565c3530msh540aa5917d83d32p15f952jsn233e528b8ff7")
                            println(i)
                            i = i + 1
                        }


                       // val ourlist = newsResponse.articles.toList()
                        Log.d(TAG, "Array length is ${newsResponse.page}");
                        Log.d(TAG, "AAAAAAA is ${i}");

*/
                        //

                        // ограничиться 25?
                        Log.d(TAG, "AAAAAAA1 is ${newsResponse.articles.size}");

                        newsAdapter.differ.submitList(newsResponse.articles.toList().distinctBy { it.title })

                      //  newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.total_hits / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.breakingNewsPage == totalPages

                        if(isLastPage){
                            rvBreakingNews.setPadding(0,0,0,0)
                        }


//  newsAdapter.differ.submitList(newsResponse.articles.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                       // Toast.makeText(activity, "An error occured $message", Toast.LENGTH_SHORT).show()
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
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 3           // HERE IS THE SOLUTION
            //val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
           // val shouldPaginate = true


            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling


            if(shouldPaginate){
                viewModel.getBreakingNews("art & graffiti", viewModel.breakingNewsPage, "free-news.p.rapidapi.com", "d1565c3530msh540aa5917d83d32p15f952jsn233e528b8ff7")
                isScrolling = false
            }

        }

    }




    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(this.requireActivity())
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsListFragment.scrollListener)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
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

