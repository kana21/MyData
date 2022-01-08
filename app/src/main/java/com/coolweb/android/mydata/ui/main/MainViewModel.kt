package com.coolweb.android.mydata.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.coolweb.android.mydata.LOG_TAG
import com.coolweb.android.mydata.data.Book
import com.coolweb.android.mydata.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val listType = Types.newParameterizedType(
        List::class.java,
        Book::class.java
    )

    init {
//        val text = FileHelper.getTextFromResources(app, R.raw.book_data)
        val text = FileHelper.getTextFromAssets(app, "book_data.json")
//        Log.i(LOG_TAG, text)
        parseText(text)
    }

    fun parseText(text: String) {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Book>> = moshi.adapter(listType)
        val bookData = adapter.fromJson(text)
        for (book in bookData ?: emptyList()) {
            Log.i(LOG_TAG, "${book.name} (\$${book.price})")
        }
    }
}