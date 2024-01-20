package com.example.resultsbrowser.network

import com.example.resultsbrowser.model.SportsResults
import retrofit2.Call
import retrofit2.http.POST

interface APIService {

    @POST
    fun getLatest(): Call<SportsResults>
}