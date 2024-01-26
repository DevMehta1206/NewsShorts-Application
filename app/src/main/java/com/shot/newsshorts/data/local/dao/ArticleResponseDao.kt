package com.shot.newsshorts.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shot.newsshorts.data.entity.Article

@Dao
interface ArticleResponseDao {

    @Query("SELECT * FROM news_response_table")
    fun getAllArticles(): PagingSource<Int, Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllArticles(articles: List<Article>)

    @Query("DELETE FROM news_response_table")
    suspend fun deleteAllArticles()
}