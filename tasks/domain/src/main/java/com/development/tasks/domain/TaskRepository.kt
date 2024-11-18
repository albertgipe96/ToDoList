package com.development.tasks.domain

import com.development.core.domain.result.DataError
import com.development.core.domain.result.EmptyDataResult
import com.development.core.domain.result.Result
import com.development.core.domain.model.Task

interface TaskRepository {
    suspend fun getAllTasks(): Result<List<Task>, DataError>
    suspend fun getTask(id: Int): Result<Task, DataError>
    suspend fun storeTask(task: Task): EmptyDataResult<DataError>
    suspend fun updateTask(task: Task): EmptyDataResult<DataError>
}