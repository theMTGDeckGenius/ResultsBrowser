package com.example.resultsbrowser.di

import android.content.Context
import com.example.resultsbrowser.ResultsBrowserApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): ResultsBrowserApplication {
        return app as ResultsBrowserApplication
    }

}