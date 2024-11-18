package com.development.tasks.data.datasource

import com.development.core.domain.result.DataError
import com.development.core.domain.result.EmptyDataResult
import com.development.core.domain.result.Result
import com.development.core.domain.model.Task

interface LocalTaskDataSource {
    suspend fun getAllTasks(): Result<List<Task>, DataError>
    suspend fun getTask(id: Int): Result<Task, DataError>
    suspend fun upsertTask(task: Task): EmptyDataResult<DataError>
}