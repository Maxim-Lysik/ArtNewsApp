package com.example.artnewsapplicationtorelease.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sitesources")
data class SiteSources (

    @PrimaryKey
    @ColumnInfo(name = "id")
    val _id: String,
    @ColumnInfo(name = "score") val _score: Double,


    /*

    foxnews.com

    express.co.uk
yahoo.com
olympics.com
redbull.com
dailymail.co.uk


*/
    )