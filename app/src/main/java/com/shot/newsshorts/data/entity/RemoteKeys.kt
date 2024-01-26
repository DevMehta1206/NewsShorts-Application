package com.shot.newsshorts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shot.newsshorts.data.AppConstants.REMOTE_KEYS_TABLE

@Entity(tableName = REMOTE_KEYS_TABLE)
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)