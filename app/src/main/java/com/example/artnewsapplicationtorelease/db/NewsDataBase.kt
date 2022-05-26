package com.example.artnewsapplicationtorelease.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.artnewsapplicationtorelease.Article


@Database(entities = [Article::class], version = 1)
abstract class NewsDataBase: RoomDatabase() {

    abstract fun getItemDao():ItemDao
    companion object{
        @Volatile
        private var instance:NewsDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {instance = it}
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDataBase::class.java,
                "news_db.db"
            ).build()


    }

}