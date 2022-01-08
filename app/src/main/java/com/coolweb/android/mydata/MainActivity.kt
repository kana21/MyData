package com.coolweb.android.mydata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.coolweb.android.mydata.data.Book
import com.coolweb.android.mydata.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        val book = Book(
            bookName ="Head First Kotlin: A Brain-Friendly Guide ",
            imageFile = "book04",
            author = "Dawn Griffiths, David Griffiths",
            description = "Head First Kotlin is a complete introduction to coding in Kotlin.",
            ISBN = "1491996692",
            price = 25.25,
            pages = 450
        )
        Log.i("bookLogging", book.toString())
        */

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}