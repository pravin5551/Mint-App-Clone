package com.nero.mint.data.remote

import com.nero.mint.newsPojo.ArticlesItem
import com.nero.mint.newsPojo.DataItem
import com.nero.mint.newsPojo.NewArticlePojo.NewArticlesResponse

interface OnItemClickListener {
    fun onSaved(articlesItem: ArticlesItem)

    fun selected(articlesItem: ArticlesItem)

    fun onButtonClicked(name:String)

    fun onTrendingArticleSelected(newsArticlesResponse: NewArticlesResponse)

    fun onPremiumArticleSelected(dataItem: DataItem)

}