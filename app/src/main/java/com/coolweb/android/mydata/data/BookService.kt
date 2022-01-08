package com.coolweb.android.mydata.data

import retrofit2.Response
import retrofit2.http.GET

interface BookService {
    @GET("/feed/book_data.json")
    suspend fun getMonsterData(): Response<List<Book>>
}