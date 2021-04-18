package com.nero.mint.data.remote

import com.nero.mint.data.remote.DataBase.BookmarkEntity
import com.nero.mint.data.remote.DataBase.NewsArticlesEntity
import com.nero.mint.newsPojo.ArticlesItem
import com.nero.mint.newsPojo.DataItem
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse

interface OnItemClickListener {
    fun onSaved(articlesItem: ArticlesItem)

    fun selected(articlesItem: ArticlesItem)

    fun onButtonClicked(name: String)

    fun onTrendingArticleSelected(newsArticlesResponse: NewArticlesResponse)

    fun onPremiumArticleSelected(dataItem: DataItem)


    fun addBookmarks(articlesItem: ArticlesItem)

    fun addBookmarks(dataItem: DataItem)

    fun addBookmarks(newsArticlesResponse: NewArticlesResponse)

    fun deleteBookmarks(articlesItem: ArticlesItem)

    fun deleteBookmarks(dataItem: DataItem)

    fun deleteBookmarks(newsArticlesResponse: NewArticlesResponse)

    fun deleteBookMarkEntity(bookmarkEntity: BookmarkEntity)

    fun selectBookMarkEntity(bookmarkEntity: BookmarkEntity)

    fun selectArticleEntity(articlesEntity: NewsArticlesEntity)



}