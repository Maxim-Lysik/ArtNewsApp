package com.example.artnewsapplicationtorelease.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "articles")
data class Article(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val _id: String,
    @ColumnInfo(name = "score") val _score: Double,
    @ColumnInfo(name = "author") val author: String,
  //  @ColumnInfo(name = "authors") val authors: List<String>,
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
): Serializable {



    override fun hashCode(): Int {
        var result = is_opinion.hashCode()
       /* result = 31 * result + location.hashCode()
        if (name.isNotEmpty()) {
            result = 31 * result + name.hashCode()
        }
        if (addressRegion.isNotEmpty()) {
            result = 31 * result + addressRegion.hashCode()
        }
        if (addressLocality.isNotEmpty()) {
            result = 31 * result + addressLocality.hashCode()
        }
        if (streetAddress.isNotEmpty()) {
            result = 31 * result + streetAddress.hashCode()
        }
        if (postalCode.isNotEmpty()) {
            result = 31 * result + postalCode.hashCode()
        }
        if (category.isNotEmpty()) {
            result = 31 * result + category.hashCode()
        }
        if (products.isNotEmpty()) {
            result = 31 * result + products.hashCode()
        }
*/
        return result
    }


}




// @ColumnInfo(name = "author") val author: Any,
// @ColumnInfo(name = "authors") val authors: List<Any>,