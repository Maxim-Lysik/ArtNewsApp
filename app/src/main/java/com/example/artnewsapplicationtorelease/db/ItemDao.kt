package com.example.artnewsapplicationtorelease.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artnewsapplicationtorelease.models.Article


@Dao
interface ItemDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun upsert(article: Article): Long
   //suspend fun upsert(article: Article): String

   @Query("SELECT * FROM articles")
   fun getAllArticles(): LiveData<List<Article>>

   @Delete
   fun deleteArticle(article: Article)
  // suspend fun deleteArticle(article: Article)


}