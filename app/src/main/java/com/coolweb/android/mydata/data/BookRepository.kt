package com.coolweb.android.mydata.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.coolweb.android.mydata.LOG_TAG
import com.coolweb.android.mydata.WEB_SERVICE_URL
import com.coolweb.android.mydata.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BookRepository(val app: Application) {

    val bookData = MutableLiveData<List<Book>>()

    private val listType = Types.newParameterizedType(
        List::class.java,
        Book::class.java
    )

    init {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

    fun getBookData(): List<Book> {
        val text = FileHelper.getTextFromAssets(app, "book_data.json")
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Book>> = moshi.adapter(listType)
        return adapter.fromJson(text) ?: emptyList()
    }

    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(BookService::class.java)
            val serviceData = service.getMonsterData().body() ?: emptyList()
            Log.i(LOG_TAG, serviceData.toString())
            bookData.postValue(serviceData)
        }
    }

    @Suppress("DEPRECIATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}