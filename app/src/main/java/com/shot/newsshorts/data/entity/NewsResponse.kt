package com.shot.newsshorts.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shot.newsshorts.data.AppConstants.NEWS_RESPONSE_TABLE
import kotlinx.serialization.Serializable



data class NewsResponse(
    val status:String,
    val totalResults:Int,
    val articles:List<Article>
)

@Serializable
@Entity(tableName = NEWS_RESPONSE_TABLE)
data class Article(
    val author:String?,

    val title:String?,
    val description:String?,
    val url:String?,
    val urlToImage:String?,
    @PrimaryKey(autoGenerate = false)
    val publishedAt:String,
    val content:String?,
    @Embedded
    val source:Source?
)

data class Source(
    val id:String?,
    val name:String?
)