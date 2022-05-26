package com.example.artnewsapplicationtorelease.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artnewsapplicationtorelease.Article


@Dao
interface ItemDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsert(article: Article): String

   @Query("SELECT * FROM articles")
   fun getAllArticles(): LiveData<List<Article>>

   @Delete
   suspend fun deleteArticle(article: Article)


}