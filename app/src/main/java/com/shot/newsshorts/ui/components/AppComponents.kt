package com.shot.newsshorts.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import coil.compose.AsyncImage
import com.shot.newsshorts.R
import com.shot.newsshorts.data.entity.Article
import com.shot.newsshorts.data.entity.NewsResponse
import com.shot.newsshorts.ui.theme.Purple40

@Composable
fun Loader(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = Purple40
        )
    }
}

@Composable
fun NewsList(response: NewsResponse){
    LazyColumn {
        items(response.articles){article ->
            NormalTextComponent(textValue = article.title?:"N   A")
        }
    }
}

@Composable
fun NormalTextComponent(textValue : String?){
    Text(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
        ,text = textValue ?: "NULL",
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace
        )
    )
}

@Composable
fun HeadingTextComponent(textValue : String?){
    Text(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
        ,text = textValue!!,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    )
}

@Composable
fun AuthorDetailsComponent(authorName : String?,source:String?){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, bottom = 24.dp)){

        authorName?.also {
            Text(text = it)
        }

        Spacer(modifier = Modifier.weight(1f))

        source?.also {
            Text(text = it)
        }


    }
}


@Composable
fun NewsRowComponent(article: Article){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(Color.White)){

        AsyncImage(modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
            model = article,
            contentDescription = " ",
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder)
        )

        Spacer(modifier = Modifier.size(20.dp))
         HeadingTextComponent(textValue = article.title?: "")

        Spacer(modifier = Modifier.size(10.dp))

        NormalTextComponent(textValue = article.description)

        Spacer(modifier =Modifier.weight(1f))

        AuthorDetailsComponent(authorName = article.author, source = article.source?.name)
    }
}

@Composable
fun EmptyStateComponent(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){

        Image(painter = painterResource(id = R.drawable.icons8_news_256), contentDescription = "No News Found")

        Spacer(modifier = Modifier.size(10.dp))

        HeadingTextComponent(textValue = "No News as of now Please check in some time!")
    }
}



@Preview
@Composable
fun EmptyStateComponentPreview(){
    EmptyStateComponent()
}
