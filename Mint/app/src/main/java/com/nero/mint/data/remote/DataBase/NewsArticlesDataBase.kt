package com.nero.mint.data.remote.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsArticlesEntity::class,BookmarkEntity::class], version = 1)
abstract class NewsArticlesDataBase : RoomDatabase() {

    abstract fun getNewsArticlesDao(): NewsDAO

    companion object {

        var INSTANCE: NewsArticlesDataBase? = null

        fun getNewsArticlesDatabse(context: Context): NewsArticlesDataBase {

            if (INSTANCE == null) {

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    NewsArticlesDataBase::class.java,
                    "Mint_DataBase"
                )

                INSTANCE = builder.build()

                return INSTANCE!!


            } else

                return INSTANCE!!


        }


    }


}