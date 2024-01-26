package com.shot.newsshorts.ui.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shot.newsshorts.data.AppConstants
import com.shot.newsshorts.data.api.ApiService
import com.shot.newsshorts.data.datasource.NewsDataSource
import com.shot.newsshorts.data.entity.Article
import com.shot.newsshorts.data.entity.NewsResponse
import com.shot.newsshorts.data.local.NewsResponseDatabase
import com.shot.newsshorts.data.pagging.NewsResponseRemoteMediator
import com.shot.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsResponseDatabase: NewsResponseDatabase
) {

//    suspend fun getNewsHeadline(country: String): Response<NewsResponse>{
//        return newsDataSource.getNewsHeadline(country)
//    }

//    suspend fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> {
//        return flow {
//            emit(ResourceState.Loading())
//
//            val response = newsDataSource.getNewsHeadline(country)
//
//            if (response.isSuccessful && response.body() != null) {
//                emit(ResourceState.Success(response.body()!!))
//            } else {
//                emit(ResourceState.Error("Error fetching news data"))
//            }
//        }.catch{e ->
//            emit(ResourceState.Error(e.localizedMessage ?: "some error in flow"))
//        }
//
//    }

//      fun getNewsHeadline(): Flow<PagingData<Article>> {
//        return newsDataSource.getNewsHeadline()
//    }

   @OptIn(ExperimentalPagingApi::class)
   fun getNewsHeadline(): Flow<PagingData<Article>> {
       val data = newsResponseDatabase.newsResponseDao().getAllArticles()
      
//       Log.d("DEMO METHOD",data.)
        val pagingSourceFactory = { newsResponseDatabase.newsResponseDao().getAllArticles() }

        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = NewsResponseRemoteMediator(
                apiService,
                newsResponseDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


}