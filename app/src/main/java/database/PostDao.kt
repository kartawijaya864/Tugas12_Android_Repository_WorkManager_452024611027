package com.example.android_repository_workmanager.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_repository_workmanager.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<Post>)

    @Query("DELETE FROM posts")
    suspend fun deleteAll()
}