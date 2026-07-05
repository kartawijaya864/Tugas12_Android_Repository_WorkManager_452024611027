package com.example.android_repository_workmanager.repository

import com.example.android_repository_workmanager.database.PostDao
import com.example.android_repository_workmanager.model.Post
import com.example.android_repository_workmanager.network.ApiService
import kotlinx.coroutines.flow.Flow

class PostRepository(
    private val api: ApiService,
    private val dao: PostDao
) {

    val posts: Flow<List<Post>> = dao.getAllPosts()

    suspend fun refreshPosts() {
        val posts = api.getPosts()
        dao.deleteAll()
        dao.insertPosts(posts)
    }
}