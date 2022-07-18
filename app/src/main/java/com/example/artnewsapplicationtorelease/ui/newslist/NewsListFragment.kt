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
import com.example.artnewsapplicationtorelease.ui.NewsViewModel
import com.example.artnewsapplicationtorelease.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.example.artnewsapplicationtorelease.utils.Resource
import com.google.gson.Gson
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

        val sharedPreference =
            this.activity!!.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        sharedPreference.getString("username", "defaultName")
        sharedPreference.getLong("l", 1L)


        /*  val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)*/


        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK].toInt()
        val time = calendar.time
        val time2 = calendar[Calendar.HOUR_OF_DAY].toInt()

        //  FOR COUNTER FOR ONE DAY

        val c = Calendar.getInstance()
        c.add(Calendar.DATE, 1)
        //c.add(Calendar.DAY_OF_WEEK).to
        Log.d(TAG, "TOMORRA IS is ${c}");

        sharedPreference.getInt("Counter_for_day", 0) // COUNTER THAT WE WILL USE


      /*  var comparable1 = calendar[Calendar.DAY_OF_WEEK].toInt()
        if (sharedPreference.getInt("Comparable2", 0)!= 0 && sharedPreference.getInt("Comparable2", 0) != comparable1){

            sharedPreference.getInt("Counter_for_day", 0) // EMPTIED COUNTER
            var suki: String = "ochistili"
            sharedPreference.edit().putInt("Comparable2", comparable1) //
        } else{


        }




        var count_today: Int = 0
        var counter_todays = sharedPreference.getInt("$day", 0)
        val day_last_counter = calendar[Calendar.DAY_OF_WEEK].toInt()
        sharedPreference.edit().putInt("Comparable2", day_last_counter).commit()  // last day we entered application
*/

        ///  FOR COUNTER FOR ONE DAY



        Log.d(TAG, "TIME is ${time2}");


        var counter_general: Int
        var counter_start: Int
        var counter_monday: Int
        var counter_tuesday: Int
        var counter_wednesday: Int
        var counter_thursday: Int
        var counter_friday: Int
        var counter_saturday: Int
        var counter_sunday: Int
        counter_general = sharedPreference.getInt("General", 0)
        counter_start = sharedPreference.getInt("Ass", 0)
        counter_sunday = sharedPreference.getInt("Sunday", 0)
        counter_monday = sharedPreference.getInt("Monday", 0)
        counter_tuesday = sharedPreference.getInt("Tuesday", 0)
        counter_wednesday = sharedPreference.getInt("Wednesday", 0)
        counter_thursday = sharedPreference.getInt("Thursday", 0)
        counter_friday = sharedPreference.getInt("Friday", 0)
        counter_saturday = sharedPreference.getInt("Saturday", 0)

        // VARIABLES FOR DAY PARTS

        var counter_morning = sharedPreference.getInt("Morning", 0)
        var counter_afternoon = sharedPreference.getInt("Afternoon", 0)
        var counter_evening = sharedPreference.getInt("Evening", 0)
        var counter_night = sharedPreference.getInt("Night", 0)

        // VARIABLES FOR CARDVIEW
        var card_title = sharedPreference.getString("Card_title", "DEFAULT");
        // var card_pict = sharedPreference.getString("Card_pict", "DEFAULT");

        var our_object = sharedPreference.getString("Our_object", "DEFAULT");







        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
                putString("shit", "fuck")
                //putSerializable("article2", it)
            }
            findNavController().navigate(
                R.id.action_navigation_news_to_articleFragment, bundle
            )


            var main_counter = sharedPreference.getInt("MAIN_COUNTER", 0)
            sharedPreference.getInt("Counter_for_day", 0)



            var comparable1 = calendar[Calendar.DAY_OF_WEEK].toInt()
            if (sharedPreference.getInt("Comparable2", 0)!= 0 && sharedPreference.getInt("Comparable2", 0) != comparable1){


                sharedPreference.edit().putInt("Counter_for_day", 1).commit() // EMPTIED COUNTER
                sharedPreference.edit().putInt("MAIN_COUNTER", 1).commit()

                var suki: String = "ochistili"
                sharedPreference.edit().putInt("Comparable2", comparable1) //
            } else{
                main_counter = main_counter + 1
                sharedPreference.edit().putInt("MAIN_COUNTER", main_counter).commit()
                sharedPreference.edit().putInt("Counter_for_day", sharedPreference.getInt("MAIN_COUNTER",0)).commit()

            }


            val day_last_counter = calendar[Calendar.DAY_OF_WEEK].toInt()
            sharedPreference.edit().putInt("Comparable2", day_last_counter).commit()  // last day we entered application

            Log.d(TAG, "TIME is ${time2}");
            Log.d(TAG, "TIME is ${time2}");






            val gson = Gson()
            val json: String = gson.toJson(it)
            sharedPreference.edit().putString("Our_object", json).commit()



            counter_general += 1
            sharedPreference.edit().putInt("General", counter_general).commit()

            sharedPreference.edit().putString("Card_title", it.title).commit()
            sharedPreference.edit().putString("Card_pict", it.media).commit()
            sharedPreference.edit().putString("Card_link", it.link).commit()

            // IT WORKED
            counter_start = counter_start + 1
            sharedPreference.edit().putInt("Ass", counter_start).commit()

            // HOURCOUNTERS


            when (time2) {
                in 0..4, 21, 22, 23, 24 -> {
                    counter_night = counter_night + 1
                    sharedPreference.edit().putInt("Night", counter_night).commit()

                }
                in 5..11 -> {

                    counter_morning = counter_morning + 1
                    sharedPreference.edit().putInt("Morning", counter_morning).commit()


                }
                in 12..16 -> {
                    counter_afternoon = counter_afternoon + 1
                    sharedPreference.edit().putInt("Afternoon", counter_afternoon).commit()

                }
                in 17..20 -> {

                    counter_evening = counter_evening + 1
                    sharedPreference.edit().putInt("Evening", counter_evening).commit()

                }

            }


            // DAYSCOUNTERS

            when (day) {
                1 -> {
                    counter_sunday = counter_sunday + 1
                    sharedPreference.edit().putInt("Sunday", counter_sunday).commit()

                }
                2 -> {

                    counter_monday = counter_monday + 1
                    sharedPreference.edit().putInt("Monday", counter_monday).commit()


                }
                3 -> {
                    counter_tuesday = counter_tuesday + 1
                    sharedPreference.edit().putInt("Tuesday", counter_tuesday).commit()

                }
                4 -> {

                    counter_wednesday = counter_wednesday + 1
                    sharedPreference.edit().putInt("Wednesday", counter_wednesday).commit()


                }
                5 -> {

                    counter_thursday = counter_thursday + 1
                    sharedPreference.edit().putInt("Thursday", counter_thursday).commit()


                }
                6 -> {
                    counter_friday = counter_friday + 1
                    sharedPreference.edit().putInt("Friday", counter_friday).commit()

                }
                7 -> {

                    counter_saturday = counter_saturday + 1
                    sharedPreference.edit().putInt("Saturday", counter_saturday).commit()
                }
            }


        }



        /// PART FOR LIST REFRESHING
/*
        var time_point1 = sharedPreference.getInt("Point1", 0)
        var time_point2 = sharedPreference.getInt("Point2", 0)

        if(time_point1==0){
            val calendar_point1 = Calendar.getInstance()
            val day = calendar_point1[Calendar.DAY_OF_WEEK].toInt()
            sharedPreference.edit().putInt("Point1", day).commit()
        }

        if(time_point1!=0 && time_point2== null){
            val calendar_point2 = Calendar.getInstance()
            val day2 = calendar_point2[Calendar.DAY_OF_WEEK].toInt()
            sharedPreference.edit().putInt("Point2", day2).commit()
        }
      /* else if(time_point1!=0 && time_point2!=0){
            // здесь обмен значениями делаем
            time_point1 = sharedPreference.getInt("Point2", 0)
            val calendar_point3 = Calendar.getInstance()
            val day3 = calendar_point3[Calendar.DAY_OF_WEEK].toInt()
            sharedPreference.edit().putInt("Point2", day3).commit()

        }*/

        Log.d(TAG, "TIMEPOINT 1 IS ${time_point1}");
        Log.d(TAG, "TIMEPOINT 2 IS ${time_point2}");


*/






        viewModel.searchedNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
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

                        newsAdapter.differ.submitList(
                            newsResponse.articles.toList().distinctBy { it.title })

                        //  newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.total_hits / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.breakingNewsPage == totalPages

                        if (isLastPage) {
                            rvBreakingNews.setPadding(0, 0, 0, 0)
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






        fun updateBarchart(hour: Int) {


            when (hour) {
                in 1..4, 21, 22, 24 -> {
                    counter_night = counter_night + 1
                    sharedPreference.edit().putInt("Night", counter_night).commit()

                }
                in 5..11 -> {

                    counter_morning = counter_morning + 1
                    sharedPreference.edit().putInt("Morning", counter_morning).commit()


                }
                in 12..16 -> {
                    counter_afternoon = counter_afternoon + 1
                    sharedPreference.edit().putInt("Afternoon", counter_afternoon).commit()

                }
                in 17..20 -> {

                    counter_evening = counter_evening + 1
                    sharedPreference.edit().putInt("Evening", counter_evening).commit()

                }

            }
        }


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
            val isTotalMoreThanVisible = totalItemCount >= 4       // HERE IS THE SOLUTION
            //val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
           // val shouldPaginate = true

            // pagenumber
            var page_number = viewModel.breakingNewsPage  // page_number was added later

            val pages_not_more_than_two = page_number <= 1 // pages_not_more_than_two was added later


            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling && pages_not_more_than_two   // pages_not_more_than_two was added later


            if(shouldPaginate){
                viewModel.getBreakingNews("skateboarding", viewModel.breakingNewsPage, "en", "free-news.p.rapidapi.com", "d1565c3530msh540aa5917d83d32p15f952jsn233e528b8ff7")
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















    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

