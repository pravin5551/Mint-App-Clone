package com.nero.mint.data.remote.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDAO {


    @Insert
    fun insertArticles(newsArticlesEntity: NewsArticlesEntity)



    @Insert
    fun insertBookMarks(bookmarkEntity: BookmarkEntity)


    @Query(value ="select * from Mint")
    fun getArticles(): LiveData<List<NewsArticlesEntity>>

    @Query(value ="select * from Mint_Bookmarks")
    fun getBookmarks(): LiveData<List<BookmarkEntity>>

    @Delete
    fun deleteBookmarks(bookmarkEntity: BookmarkEntity)


}