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
import com.example.artnewsapplicationtorelease.models.DayData
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

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_artnews) as NavHostFragment
        val navController = navHostFragment.navController

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



// Testing71
        

        if(sharedPreference.getString("Point1", "") == ""){
        sharedPreference.edit().putString("Point1", string_for_date).commit()
       }
        else if(sharedPreference.getString("Point1", "") != "" && sharedPreference.getString("Point2", "") == "" ){

            sharedPreference.edit().putString("Point2", string_for_date).commit()

        }
        else if( (sharedPreference.getString("Point1", "") != "" && sharedPreference.getString("Point2", "") != "")
                  && fullDateFormatter.parse(sharedPreference.getString("Point2", ""))!! >= fullDateFormatter.parse(sharedPreference.getString("Point1", ""))   ){

            sharedPreference.edit().putString("Point1", sharedPreference.getString("Point2", "")).commit()
            sharedPreference.edit().putString("Point2", string_for_date).commit()

            var timee1 = fullDateFormatter.parse(sharedPreference.getString("Point1", "")).toString()
            var timee2 = fullDateFormatter.parse(sharedPreference.getString("Point2", "")).toString()
            var day_of_week1 = timee1.substring(0,3)
            var day_of_week2 = timee2.substring(0,3)
            var converted_day_of_week1: Int = fromStringtoInt(day_of_week1)
            var converted_day_of_week2: Int = fromStringtoInt(day_of_week2)



            if(converted_day_of_week2 < converted_day_of_week1){

                sharedPreference.edit().putInt("Sunday", 0).commit()
                sharedPreference.edit().putInt("Monday", 0).commit()
                sharedPreference.edit().putInt("Tuesday", 0).commit()
                sharedPreference.edit().putInt("Wednesday", 0).commit()
                sharedPreference.edit().putInt("Thursday", 0).commit()
                sharedPreference.edit().putInt("Friday", 0).commit()
                sharedPreference.edit().putInt("Saturday", 0).commit()

                var dayData1: DayData = DayData(1, "Sunday", 0)
                viewModel.putDate(dayData1)
                var dayData2: DayData = DayData(2, "Monday", 0)
                viewModel.putDate(dayData2)
                var dayData3: DayData = DayData(3, "Tuesday", 0)
                viewModel.putDate(dayData3)
                var dayData4: DayData = DayData(4, "Wednesday", 0)
                viewModel.putDate(dayData4)
                var dayData5: DayData = DayData(5, "Thursday", 0)
                viewModel.putDate(dayData5)
                var dayData6: DayData = DayData(6, "Friday", 0)
                viewModel.putDate(dayData6)
                var dayData7: DayData = DayData(7, "Saturday", 0)
                viewModel.putDate(dayData7)

                sharedPreference.edit().putInt("Night", 0).commit()
                sharedPreference.edit().putInt("Morning", 0).commit()
                sharedPreference.edit().putInt("Afternoon", 0).commit()
                sharedPreference.edit().putInt("Evening", 0).commit()

            }

            var timee12 = sharedPreference.getString("Point1", "")
            var timee22 = sharedPreference.getString("Point2", "")

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


    //test1

    override fun onStart() {
        super.onStart()
        val sharedPreference =
            getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var time_point1 = sharedPreference.getString("Point1", "")
        var time_point2 = sharedPreference.getString("Point2", "")
    }


}

