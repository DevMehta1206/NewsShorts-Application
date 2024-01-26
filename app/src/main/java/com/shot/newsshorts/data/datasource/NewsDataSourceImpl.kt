package com.shot.newsshorts.data.datasource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shot.newsshorts.data.AppConstants.ITEMS_PER_PAGE
import com.shot.newsshorts.data.api.ApiService
import com.shot.newsshorts.data.entity.Article
import com.shot.newsshorts.data.entity.NewsResponse
import com.shot.newsshorts.data.local.NewsResponseDatabase
import com.shot.newsshorts.data.pagging.NewsResponseRemoteMediator
import com.shot.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsResponseDatabase: NewsResponseDatabase
) : NewsDataSource {
    //    override suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
//        val res = apiService.getNewsHeadline(country)
//        Log.d("Testintg",res.body().toString())
//        return res
//
//    }
    @OptIn(ExperimentalPagingApi::class)
    override  fun getNewsHeadline(): Flow<PagingData<Article>> {
        val pagingSourceFactory = { newsResponseDatabase.newsResponseDao().getAllArticles() }
        return Pager(
            config = PagingConfig(pageSize =   ITEMS_PER_PAGE ),
            remoteMediator = NewsResponseRemoteMediator(
                 apiService,
                newsResponseDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}