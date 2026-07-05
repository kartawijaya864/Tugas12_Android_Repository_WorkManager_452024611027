package com.example.android_repository_workmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android_repository_workmanager.repository.PostRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class PostViewModel(
    repository: PostRepository
) : ViewModel() {

    val posts = repository.posts.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
}

class PostViewModelFactory(
    private val repository: PostRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(repository) as T
    }
}