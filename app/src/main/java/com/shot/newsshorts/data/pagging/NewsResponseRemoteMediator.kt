package com.shot.newsshorts.data.pagging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.shot.newsshorts.data.AppConstants.ITEMS_PER_PAGE
import com.shot.newsshorts.data.api.ApiService
import com.shot.newsshorts.data.entity.Article
import com.shot.newsshorts.data.entity.RemoteKeys
import com.shot.newsshorts.data.local.NewsResponseDatabase

@ExperimentalPagingApi
class NewsResponseRemoteMediator(
    private val newsApi: ApiService,
    private val newsResponseDatabase: NewsResponseDatabase
) : RemoteMediator<Int, Article>() {

    private val remoteKeysDao = newsResponseDatabase.remoteKeysDao()
    private val articleResponseDao = newsResponseDatabase.newsResponseDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = newsApi.getNewsHeadline(page = currentPage, pageSize = ITEMS_PER_PAGE).body()?.articles
            val endOfPaginationReached = response?.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached == true) null else currentPage + 1

            newsResponseDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleResponseDao.deleteAllArticles()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response?.map { article ->
                   RemoteKeys(
                        id = article.publishedAt,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                keys?.let { remoteKeysDao.addAllRemoteKeys(remoteKeys = it) }
                response?.let { articleResponseDao.addAllArticles(articles = it) }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached!!)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Article>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.publishedAt?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Article>
    ): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                remoteKeysDao.getRemoteKeys(id = unsplashImage.publishedAt)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Article>
    ): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                remoteKeysDao.getRemoteKeys(id = unsplashImage.publishedAt)
            }
    }
}