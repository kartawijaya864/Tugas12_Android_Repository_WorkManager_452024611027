package com.example.android_repository_workmanager.network

import com.example.android_repository_workmanager.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}

