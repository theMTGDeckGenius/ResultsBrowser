package com.example.resultsbrowser.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.resultsbrowser.model.SportsResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class SportsResultsRepository {

    //todo replace with object for results
    // or delete
    var postInfoMutableList: MutableLiveData<SportsResults> = MutableLiveData()

    @Inject
    lateinit var retrofit: Retrofit

    fun fetchPostInfoList(): LiveData<SportsResults> {
        val apiService: APIService = retrofit.create(APIService::class.java)
        val postListInfo: Call<SportsResults> = apiService.getLatest()
        postListInfo.enqueue(object : Callback<SportsResults> {
            override fun onFailure(call: Call<SportsResults>, t: Throwable) {
                Log.d("RetroRepository", "Failed:::" + t.message)
            }

            override fun onResponse(call: Call<SportsResults>, response: Response<SportsResults>) {
                //TODO implement error handeling
                val postInfoList = response.body()
                postInfoMutableList.value = postInfoList

            }
        })

        return postInfoMutableList

    }


}