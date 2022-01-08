package com.coolweb.android.mydata

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coolweb.android.mydata.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.bookData.observe(
            this,
            {
                val bookNames = StringBuilder()
                for (book in it) {
                    Log.i(LOG_TAG, book.toString())
//                    bookNames.append(book.name)
                    bookNames.append(book.bookName)
                        .append("\n")
                }
                Log.i(LOG_TAG, bookNames.toString())
                val message = findViewById<TextView>(R.id.message)
                message.text = bookNames
            }
        )
    }
}