package com.shot.newsshorts.data.datasource

import androidx.paging.PagingData
import com.shot.newsshorts.data.entity.Article
import com.shot.newsshorts.data.entity.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsDataSource {

    fun getNewsHeadline(): Flow<PagingData<Article>>
}