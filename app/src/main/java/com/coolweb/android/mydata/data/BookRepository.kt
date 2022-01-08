package com.coolweb.android.mydata.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.coolweb.android.mydata.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class BookRepository(val app: Application) {

    val bookData = MutableLiveData<List<Book>>()

    private val listType = Types.newParameterizedType(
        List::class.java,
        Book::class.java
    )

    init {
        getBookData2()
    }

    fun getBookData(): List<Book> {
        val text = FileHelper.getTextFromAssets(app, "book_data.json")
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Book>> = moshi.adapter(listType)
        return adapter.fromJson(text) ?: emptyList()
    }

    fun getBookData2() {
        val text = FileHelper.getTextFromAssets(app, "book_data.json")
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Book>> = moshi.adapter(listType)
        bookData.value = adapter.fromJson(text) ?: emptyList()
    }
}