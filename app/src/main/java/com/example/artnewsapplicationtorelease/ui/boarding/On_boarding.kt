package com.example.artnewsapplicationtorelease.ui.boarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.artnewsapplicationtorelease.ArtNewsActivity
import com.example.artnewsapplicationtorelease.R
import com.example.artnewsapplicationtorelease.databinding.ActivityOnBoardingBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import java.lang.Boolean

class On_boarding : AppCompatActivity() {


    var prevStarted = "yes"
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_content_on_boarding)

    }



    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onResume() {
        super.onResume()
        val sharedpreferences = getSharedPreferences("com.example.artapp", Context.MODE_PRIVATE)
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            val editor = sharedpreferences.edit()
            editor.putBoolean(prevStarted, Boolean.TRUE)
            editor.apply()
        } else {
            moveToSecondary()
        }
    }


    fun moveToSecondary() {
        val intent = Intent(this, ArtNewsActivity::class.java)
        startActivity(intent)
    }

}