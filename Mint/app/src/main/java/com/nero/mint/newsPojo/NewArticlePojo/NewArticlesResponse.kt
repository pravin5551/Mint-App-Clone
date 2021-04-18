package com.nero.mint.newsPojo.NewArticlePojo


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class NewArticlesResponse(

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("mainheading")
	val mainheading: String,

	@field:SerializedName("subheading1")
	val subheading1: String,

	@field:SerializedName("frontimage")
	val frontimage: String,

	@field:SerializedName("image1")
	val image1: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("tags")
	val tags: String,

	@field:SerializedName("catid")
	val catid: Int,

	@field:SerializedName("newsid")
	val newsid: Int,

	@field:SerializedName("regid")
	val regid: String,

	@field:SerializedName("category")
	val category: String
)