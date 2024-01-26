package com.shot.newsshorts.di

import android.content.Context
import androidx.room.Room
import com.shot.newsshorts.data.AppConstants.NEWS_RESPONSE_DATABASE
import com.shot.newsshorts.data.local.NewsResponseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsResponseDatabase {
        return Room.databaseBuilder(
            context,
            NewsResponseDatabase::class.java,
            NEWS_RESPONSE_DATABASE
        ).build()
    }

}