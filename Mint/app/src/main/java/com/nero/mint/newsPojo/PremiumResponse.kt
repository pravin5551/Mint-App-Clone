package com.nero.mint.newsPojo


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PremiumResponse(

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("success")
	val success: Boolean
)