package com.example.resultsbrowser.network

import com.example.resultsbrowser.model.SportsResults
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {
    @POST("results")
    @Headers(
        "Content-Type: application/json"
    )
    suspend fun getLatest(): Response<SportsResults>
}