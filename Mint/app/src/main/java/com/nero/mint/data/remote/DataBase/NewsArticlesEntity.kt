package com.nero.mint.data.remote.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Mint")
class NewsArticlesEntity(
    @ColumnInfo(name = "Title") var Title: String,
    @ColumnInfo(name = "Description") var Description: String,
    @ColumnInfo(name = "Image") var Image: String,
    @ColumnInfo(name = "Date") var Date: String,
    @ColumnInfo(name = "Url") var Url: String, ) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var Id: Int? = null


}

