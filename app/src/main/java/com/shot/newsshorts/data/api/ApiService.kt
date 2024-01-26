package com.shot.newsshorts.data.api

import com.shot.newsshorts.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country :String = "in",
        @Query("page") page : Int,
        @Query("pageSize") pageSize : Int,
        @Query("apiKey") apiKey: String = "7f01302b1bc346a88778d6a066bb3e4b"
    ):Response<NewsResponse>
}

//GET https://newsapi.org/v2/top-headlines?country=us&apiKey=7f01302b1bc346a88778d6a066bb3e4b