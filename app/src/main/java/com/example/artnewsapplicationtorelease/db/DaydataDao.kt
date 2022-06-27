package com.example.artnewsapplicationtorelease.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artnewsapplicationtorelease.models.DayData

@Dao
interface DaydataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(day_data: DayData): Long
    //suspend fun upsert(article: Article): String

    @Query("SELECT * FROM dates")
    fun getAllDataObjects(): LiveData<List<DayData>>

    @Delete
    suspend fun deleteDateObject(day_data: DayData)







}