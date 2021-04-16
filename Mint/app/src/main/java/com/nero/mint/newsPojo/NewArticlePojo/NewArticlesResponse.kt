package com.nero.mint.newsPojo.NewArticlePojo


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class NewArticlesResponse(

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("mainheading")
	val mainheading: String? = null,

	@field:SerializedName("subheading1")
	val subheading1: String? = null,

	@field:SerializedName("frontimage")
	val frontimage: String? = null,

	@field:SerializedName("image1")
	val image1: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("tags")
	val tags: String? = null,

	@field:SerializedName("catid")
	val catid: Int? = null,

	@field:SerializedName("newsid")
	val newsid: Int? = null,

	@field:SerializedName("regid")
	val regid: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)