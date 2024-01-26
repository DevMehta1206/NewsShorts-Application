package com.shot.newsshorts.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.shot.newsshorts.data.entity.Article
import com.shot.newsshorts.ui.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository) : ViewModel() {




    init {
//        Log.d(TAG,"init block runs")
//        getNews()
//        viewModelScope.launch(Dispatchers.IO) {
//            var newsArticles = newsRepository.getNewsHeadline()
//        }
    }
       fun getNews() :Flow<PagingData<Article>>{

               var newsArticles = newsRepository.getNewsHeadline()

           return newsArticles
        }
    companion object{
        const val TAG = "NewsViewModel" }

}