package com.shot.newsshorts.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.shot.newsshorts.ui.components.EmptyStateComponent
import com.shot.newsshorts.ui.components.NewsRowComponent
import com.shot.newsshorts.ui.viewmodel.NewsViewModel

const val TAG = "HOME SCREEN"
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewModel : NewsViewModel = hiltViewModel()

){

    val newsResponse = newsViewModel.getNews().collectAsLazyPagingItems().itemCount
    Log.d(TAG,newsResponse.toString())
//    val artices =
    Log.d(TAG,newsResponse.toString())

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ){
        100
    }
    VerticalPager(state = pagerState, modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp
    ) {page:Int ->



//            when(newsResponse){
//
//                is ResourceState.Loading -> {
//                    Log.d(TAG,"LOADING SCREEN")
//                    Loader()
//                }
//
//                is ResourceState.Success -> {
//                    val response = (newsResponse as ResourceState.Success).data
//                    Log.d(TAG,"SUCCESS SCREEN ${response.totalResults}")
//                    if(page in 0 until response.articles.size) {
//                        Column {
//                            NewsRowComponent( response.articles[page])
//                        }
//                    }else{
//                        EmptyStateComponent()
//                    }
//                }
//
//                is ResourceState.Error -> {
//                    Log.d(TAG,"ERROR ")
//                    val error  = (newsResponse as ResourceState.Error)
//                }
//            }
//        val response = (newsResponse ).data
//                    Log.d(TAG,"SUCCESS SCREEN ${response.totalResults}")
//                    if(page in 0 until response.articles.size) {
//                        Column {
//                            NewsRowComponent( response.articles[page])
//                        }
//                    }

        if (newsResponse != null){
//            NewsRowComponent( article = newsResponse[page])

            EmptyStateComponent()
        }else{
            EmptyStateComponent()
        }
            }
        }




@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}