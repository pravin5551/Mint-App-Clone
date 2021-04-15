package com.nero.mint.data.remote

import com.nero.mint.newsPojo.ArticlesItem

interface OnItemClickListener {
    fun onSaved(articlesItem: ArticlesItem)

    fun selected(articlesItem: ArticlesItem)

}