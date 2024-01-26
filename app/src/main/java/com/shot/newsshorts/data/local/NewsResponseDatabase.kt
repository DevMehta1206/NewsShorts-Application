package com.shot.newsshorts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shot.newsshorts.data.entity.Article
import com.shot.newsshorts.data.entity.RemoteKeys
import com.shot.newsshorts.data.local.dao.ArticleResponseDao
import com.shot.newsshorts.data.local.dao.RemoteKeysDao

@Database(entities = [Article::class, RemoteKeys::class], version = 1)
abstract class NewsResponseDatabase : RoomDatabase() {

    abstract fun newsResponseDao(): ArticleResponseDao

    abstract fun remoteKeysDao(): RemoteKeysDao


}