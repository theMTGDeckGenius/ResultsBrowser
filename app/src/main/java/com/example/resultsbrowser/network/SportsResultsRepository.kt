package com.example.resultsbrowser.network

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.resultsbrowser.ResultsBrowserApplication
import com.example.resultsbrowser.model.SportsResults
import javax.inject.Inject
import kotlin.random.Random

class SportsResultsRepository @Inject constructor() {

    private var _sportsResultsMutableList: MutableLiveData<SportsResults?> = MutableLiveData()
    var sportsResultsMutableList: LiveData<SportsResults?> = _sportsResultsMutableList

    val isLoading = MutableLiveData<Boolean>()

    suspend fun fetchPostInfoList() {
        isLoading.value = true
        val retrofit = ResultsBrowserApplication.retrofit
        val apiService: APIService = retrofit.create(APIService::class.java)
        val response = apiService.getLatest()

        //TODO implement error handling
        if (response.isSuccessful) {
            val sportsResults = response.body()
            _sportsResultsMutableList.value = sportsResults
        }

        // This section is just to properly show off the loading transitions
        // The delay will be a random amount of time between 2 seconds and 5 seconds
        Handler(Looper.getMainLooper()).postDelayed(
            {
                isLoading.value = false
            },
            Random.nextLong(2000, 5000)
        )
    }

    fun createBigList() {
        isLoading.value = true

        // This section is just to properly show off the loading transitions
        // The delay will be a random amount of time between 2 seconds and 5 seconds
        Handler(Looper.getMainLooper()).postDelayed(
            {
                isLoading.value = false
            },
            Random.nextLong(2000, 5000)
        )
    }
}