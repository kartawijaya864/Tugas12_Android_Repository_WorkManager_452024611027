package com.example.android_repository_workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android_repository_workmanager.database.AppDatabase
import com.example.android_repository_workmanager.network.RetrofitInstance
import com.example.android_repository_workmanager.repository.PostRepository

class RefreshWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        return try {

            Log.d("RefreshWorker", "Worker dimulai")

            val dao = AppDatabase.getDatabase(applicationContext).postDao()

            val repository = PostRepository(
                RetrofitInstance.api,
                dao
            )

            repository.refreshPosts()

            Log.d("RefreshWorker", "Result.success()")

            Result.success()

        } catch (e: Exception) {

            Log.e("RefreshWorker", e.toString())

            Result.failure()
        }
    }
}

