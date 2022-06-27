package com.example.artnewsapplicationtorelease.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "dates")
data class DayData (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "id")
    val id: Int,
    @ColumnInfo(name = "day", defaultValue = "Monday") val day: String?,
    @ColumnInfo(name = "clicks_today", defaultValue = "0") val clicks_today: Int?,
    //@ColumnInfo(name = "clicks_general") val clicks_general: Int?,
   // @ColumnInfo(name = "days_in_month") val days_in_month: Int?

        )