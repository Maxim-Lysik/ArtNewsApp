package com.example.artnewsapplicationtorelease.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.artnewsapplicationtorelease.models.Article
import com.example.artnewsapplicationtorelease.models.DayData


@Database(entities = [Article::class, DayData::class], version = 2)
@TypeConverters(Converters::class)
abstract class NewsDataBase : RoomDatabase() {

    abstract fun getItemDao(): ItemDao
    abstract fun getDaydataDao(): DaydataDao

    companion object {
        @Volatile
        private var instance: NewsDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDataBase::class.java,
                "news_db.db"
            ).build()
    }

}