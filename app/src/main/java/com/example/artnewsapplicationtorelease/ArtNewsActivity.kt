package com.example.artnewsapplicationtorelease

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
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

        val sharedPreference =
            getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        var calendar_point1 = Calendar.getInstance()
        var day = calendar_point1[Calendar.DAY_OF_WEEK].toInt()

       // sharedPreference.edit().putInt("Point1", day).commit()
      //  sharedPreference.edit().putInt("Point2", day).commit()


        if(sharedPreference.getInt("Point1", 0) == 0){
        sharedPreference.edit().putInt("Point1", day).commit()
       }

        else if(sharedPreference.getInt("Point1", 0) != 0 && sharedPreference.getInt("Point2", 0) ==0 ){


            sharedPreference.edit().putInt("Point2", day).commit()

        }




        /// PART FOR LIST REFRESHING

/*
        val sharedPreference =
            getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        var time_point1 = sharedPreference.getInt("Point1", 0)
        var time_point2 = sharedPreference.getInt("Point2", 0)

        if(time_point1==0){
            val calendar_point1 = Calendar.getInstance()
            val day = calendar_point1[Calendar.DAY_OF_WEEK].toInt()
            sharedPreference.edit().putInt("Point1", day).commit()
        }

        //if(time_point1!=0 && time_point2== null){

        if(time_point1>0 && time_point2== 0){
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







        /* val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
          var editor = sharedPreference.edit()
          editor.putInt("Counter", 1)
          editor.commit()



          val gotthis = sharedPreference.getInt("Ass", 0)
          //sharedPreference.getLong("l",1L)

          Log.d(TAG, "SHARED ONE ${gotthis}");*/


        //val graphView1 = findViewById<GraphView>(R.id.graph_view)
       // graphView1.setData(generateRandomDataPoints())
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

    override fun onStart() {
        super.onStart()

        val sharedPreference =
            getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        var time_point1 = sharedPreference.getInt("Point1", 0)
        var time_point2 = sharedPreference.getInt("Point2", 0)

       /* if(sharedPreference.getInt("Point1", 0)==0){
            val calendar_point1 = Calendar.getInstance()
            val day = calendar_point1[Calendar.DAY_OF_WEEK].toInt()
            sharedPreference.edit().putInt("Point1", day).commit()
        }

        //if(time_point1!=0 && time_point2== null){

        if(sharedPreference.getInt("Point1", 0)>0 && sharedPreference.getInt("Point2", 0)== 0){
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

        val calendar_point2 = Calendar.getInstance()
        val day = calendar_point2[Calendar.DAY_OF_WEEK].toInt()

*/





        Log.d(TAG, "TIMEPOINT 1 IS ${time_point1}");
        Log.d(TAG, "TIMEPOINT 2 IS ${time_point2}");




    }



 /*   private fun generateRandomDataPoints(): List<DataPoint> {
        val random = Random()
        return (0..20).map {
            DataPoint(it, random.nextInt(50) + 1)
        }
    }*/



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