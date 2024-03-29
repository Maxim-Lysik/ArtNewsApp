package com.example.artnewsapplicationtorelease.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artnewsapplicationtorelease.models.DayData

@Dao
interface DaydataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(day_data: DayData): Long

    @Query("SELECT * FROM dates")
    fun getAllDataObjects(): LiveData<List<DayData>>

    @Delete
    suspend fun deleteDateObject(day_data: DayData)


    @Query("UPDATE dates SET clicks_today =:clicks_today WHERE id = :id")
    fun update(clicks_today: Int?, id: Int)


    @Query("SELECT * FROM dates WHERE id=:id ")
    fun getDayDataItem(id: Int): LiveData<DayData>


}