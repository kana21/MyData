package com.coolweb.android.mydata.data

import com.squareup.moshi.Json

data class Book (
//    @Json(name = "bookName") val name: String,
    @Json(name = "bookName") val bookName: String,
    val imageFile: String,
    val author: String,
    val description: String,
    val ISBN: String,
    val price: Double,
    val pages: Int
)