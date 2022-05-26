package com.example.artnewsapplicationtorelease.ui.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.artnewsapplicationtorelease.api.NewsAPI
import com.example.artnewsapplicationtorelease.api.RetrofitInstance
import com.example.artnewsapplicationtorelease.databinding.FragmentNewsListBinding
import com.example.artnewsapplicationtorelease.utils.Constants
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }





        GetAPISUKA()

        val buttonone: Button = binding.button1

        buttonone.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                GetAPISUKA()
            }


        }

        val api:RetrofitInstance


        return root



    }






     fun GetAPISUKA(){
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





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

