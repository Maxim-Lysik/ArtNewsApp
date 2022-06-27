package com.example.artnewsapplicationtorelease.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors


class Converters {


  /*  @TypeConverter
    fun fromString(value: String?): List<String?>? {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
*/





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

       // val yourArray: List<String> = data.split("_")
        //return Arrays.asList(data.split(",").toList()
    }





}