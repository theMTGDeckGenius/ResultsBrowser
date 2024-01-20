package com.example.resultsbrowser.di

import com.example.resultsbrowser.network.SportsResultsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {
    private var baseURL: String = "https://restest.free.beeceptor.com/results"


    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()

    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideRetroRepository(): SportsResultsRepository {
        return SportsResultsRepository()
    }

}