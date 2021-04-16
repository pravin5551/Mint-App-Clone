package com.nero.mint.newsPojo


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DataItem(

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("readMoreUrl")
	val readMoreUrl: String,

	@field:SerializedName("time")
	val time: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String
)