package com.example.resultsbrowser

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class ResultsBrowserApplication : Application() {
    private var baseURL: String = "https://restest.free.beeceptor.com/"

    companion object {
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = initDaggerComponent()

    }

    private fun initDaggerComponent(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .build()
        val gsonConverterFactory = GsonConverterFactory.create()

        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
        return retrofit
    }

}