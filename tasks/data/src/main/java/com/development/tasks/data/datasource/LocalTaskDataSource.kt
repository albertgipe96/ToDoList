package com.development.tasks.data.datasource

import com.development.core.domain.result.DataError
import com.development.core.domain.result.EmptyDataResult
import com.development.core.domain.result.Result
import com.development.core.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface LocalTaskDataSource {
    suspend fun getAllTasks(): Flow<List<Task>>
    suspend fun getTask(id: Int): Result<Task, DataError>
    suspend fun upsertTask(task: Task): EmptyDataResult<DataError>
}