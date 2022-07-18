package com.example.artnewsapplicationtorelease

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
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
import java.text.SimpleDateFormat
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
        var day_time = calendar_point1.time
        var month = calendar_point1[Calendar.MONTH] + 1
        var year = calendar_point1[Calendar.YEAR]
        var day_day = calendar_point1[Calendar.DAY_OF_MONTH]
        val corrected_date = calendar_point1.set(Calendar.MONTH,Calendar.YEAR, Calendar.DAY_OF_MONTH )
        val myFormat = "dd-MM-yyyy"
        var fullDateFormatter = SimpleDateFormat(myFormat)
        var final_date = fullDateFormatter.parse("$day_day-$month-$year")
        var string_for_date: String = ("$day_day-$month-$year").toString()




        Log.d(TAG, "CALENDAR TODAY'S ISS ${day_time}");

       // sharedPreference.edit().putInt("Point1", day).commit()
      //  sharedPreference.edit().putInt("Point2", day).commit()


        if(sharedPreference.getString("Point1", "") == ""){
        sharedPreference.edit().putString("Point1", string_for_date).commit()
       }

        else if(sharedPreference.getString("Point1", "") != "" && sharedPreference.getString("Point2", "") == "" ){


            sharedPreference.edit().putString("Point2", string_for_date).commit()

        }

      else if( (sharedPreference.getString("Point1", "") != "" && sharedPreference.getString("Point2", "") != "")
                  && fullDateFormatter.parse(sharedPreference.getString("Point2", ""))!! >= fullDateFormatter.parse(sharedPreference.getString("Point1", ""))   ){

            sharedPreference.edit().putString("Point1", sharedPreference.getString("Point2", "")).commit()  // Последнюю дату ставим на место первой
            sharedPreference.edit().putString("Point2", string_for_date).commit()
            Log.d(TAG, "USPEKH");


            var timee1 = fullDateFormatter.parse(sharedPreference.getString("Point1", "")).toString()
            var timee2 = fullDateFormatter.parse(sharedPreference.getString("Point2", "")).toString()

            var day_of_week1 = timee1.substring(0,3)
            var day_of_week2 = timee2.substring(0,3)

            var converted_day_of_week1: Int = fromStringtoInt(day_of_week1)   // Working
            var converted_day_of_week2: Int = fromStringtoInt(day_of_week2)   // Working


            // OPERATING WITH VALUES HERE

            if(converted_day_of_week2 < converted_day_of_week1){



            }





            var timee12 = sharedPreference.getString("Point1", "")
            var timee22 = sharedPreference.getString("Point2", "")




            Log.d(TAG, "HER 1 IS ${timee1}");
            Log.d(TAG, "HER 2 IS ${timee2}");

            Log.d(TAG, "SUBSTRING 1 IS ${day_of_week1}");
            Log.d(TAG, "SUBSTRING 2 IS ${day_of_week2}");

            Log.d(TAG, "INTA 1 IS ${converted_day_of_week1}");
            Log.d(TAG, "INTA 2 IS ${converted_day_of_week2}");


       }





    }


    fun fromStringtoInt(string: String):Int{
        var counter:Int = 0
        when (string) {
            "Sun" -> counter = 1
            "Mon" -> counter = 2
            "Tue" -> counter = 3
            "Wed" -> counter = 4
            "Thu" -> counter = 5
            "Fri" -> counter = 6
            "Sat" -> counter = 7
        }

return counter
    }

    override fun onStart() {
        super.onStart()

        val sharedPreference =
            getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        var time_point1 = sharedPreference.getString("Point1", "")
        var time_point2 = sharedPreference.getString("Point2", "")






        Log.d(TAG, "TIMEPOINT 1 IS ${time_point1}");
        Log.d(TAG, "TIMEPOINT 2 IS ${time_point2}");




    }






}

