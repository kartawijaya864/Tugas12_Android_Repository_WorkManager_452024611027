package com.example.android_repository_workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.android_repository_workmanager.database.AppDatabase
import com.example.android_repository_workmanager.network.RetrofitInstance
import com.example.android_repository_workmanager.repository.PostRepository
import com.example.android_repository_workmanager.ui.PostViewModel
import com.example.android_repository_workmanager.ui.PostViewModelFactory
import com.example.android_repository_workmanager.ui.theme.Android_Repository_WorkManagerTheme
import com.example.android_repository_workmanager.worker.RefreshWorker

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = AppDatabase.getDatabase(this).postDao()

        val repository = PostRepository(
            RetrofitInstance.api,
            dao
        )

        val factory = PostViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequestBuilder<RefreshWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(request)

        setContent {

            Android_Repository_WorkManagerTheme {

                val posts by viewModel.posts.collectAsState()

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(posts) { post ->

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = post.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(post.body)
                        }

                    }

                }

            }

        }
    }
}