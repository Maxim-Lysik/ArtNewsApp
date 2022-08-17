package com.example.artnewsapplicationtorelease.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors


class Converters {


    @RequiresApi(Build.VERSION_CODES.N)

    @TypeConverter
    fun fromHobbies(hobbies: List<String?>): String? {
        return hobbies.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toHobbies(data: String): List<String?>? {
        val yourArray: List<String>
        yourArray = data.split("_")
        return yourArray
    }

}