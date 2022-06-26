package com.example.artnewsapplicationtorelease

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.artnewsapplicationtorelease.databinding.ActivityMainBinding
import com.example.artnewsapplicationtorelease.db.NewsDataBase
import com.example.artnewsapplicationtorelease.repository.NewsRepository
import com.example.artnewsapplicationtorelease.ui.NewsViewModel
import com.example.artnewsapplicationtorelease.ui.NewsViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_stats.*
import kotlinx.android.synthetic.main.fragment_stats.view.*
import java.util.*

class ArtNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(NewsDataBase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


        val navView: BottomNavigationView = binding.navView

      //  val navController = findNavController(R.id.nav_host_fragment_activity_artnews)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_artnews) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news, R.id.navigation_saved, R.id.navigation_stats
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
       // val graphick_view: GraphView = GraphView(this, attr)

       /* val graphView1 = findViewById<GraphView>(R.id.graph_view)
        graphView1.setData(generateRandomDataPoints())*/

      // graphick_view.setData(generateRandomDataPoints())

       // graph_view.
        //graphick_view.setData(generateRandomDataPoints())

       // val list: List<DataPoint> = listOf(DataPoint(4,5),DataPoint(4,5), DataPoint(4,5), DataPoint(4,5), DataPoint(4,5))

       // graphick_view.setData(list)

       // var board: GraphView = GraphView(this, list)
        //graph_view.
     //   graphick_view.setData(viewModel.list2)


    }



    private fun generateRandomDataPoints(): List<DataPoint> {
        val random = Random()
        return (0..20).map {
            DataPoint(it, random.nextInt(50) + 1)
        }
    }



}

// test commmit12

/*class ArtNewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(NewsDataBase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        val navController = findNavController(R.id.nav_host_fragment_activity_artnews)
    }
}*/