package com.example.artnewsapplicationtorelease.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artnewsapplicationtorelease.models.Article


@Dao
interface ItemDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsert(article: Article): Long

   @Query("SELECT * FROM articles")
   fun getAllArticles(): LiveData<List<Article>>

   @Query("SELECT * FROM articles WHERE link =(:link)")
   fun getByLink(link: String): LiveData<Article>

   @Delete
   suspend fun deleteArticle(article: Article)

}