package com.example.artnewsapplicationtorelease

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articles")
data class Article(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val _id: String,
    @ColumnInfo(name = "score") val _score: Double,
    @ColumnInfo(name = "author") val author: Any,
    @ColumnInfo(name = "authors") val authors: List<Any>,
    @ColumnInfo(name = "clean_url")val clean_url: String,
    @ColumnInfo(name = "country")val country: String,
    @ColumnInfo(name = "is_option")val is_opinion: Boolean,
    @ColumnInfo(name = "language")val language: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "media")val media: String,
    @ColumnInfo(name = "published_date")val published_date: String,
    @ColumnInfo(name = "published_date_precision") val published_date_precision: String,
    @ColumnInfo(name = "rank") val rank: Int,
    @ColumnInfo(name = "rights")val rights: String,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "topic")val topic: String,
    @ColumnInfo(name = "twitter_account") val twitter_account: String
)