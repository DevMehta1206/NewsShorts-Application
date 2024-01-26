package com.shot.newsshorts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shot.newsshorts.data.entity.RemoteKeys


@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): RemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RemoteKeys>)

    @Query("DELETE FROM remote_keys_table")
    suspend fun deleteAllRemoteKeys()

}