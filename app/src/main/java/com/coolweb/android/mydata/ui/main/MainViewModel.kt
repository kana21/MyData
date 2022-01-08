package com.coolweb.android.mydata.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.coolweb.android.mydata.data.BookRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val bookDataRepo = BookRepository(app)
    val bookData = bookDataRepo.bookData
}