package com.development.tasks.data

import com.development.core.domain.result.DataError
import com.development.core.domain.result.EmptyDataResult
import com.development.core.domain.result.Result
import com.development.tasks.domain.TaskRepository
import com.development.core.domain.model.Task
import com.development.tasks.data.datasource.LocalTaskDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class TaskRepositoryImpl @Inject constructor(
    private val localTaskDataSource: LocalTaskDataSource
) : TaskRepository {

    override suspend fun getAllTasks(): Flow<List<Task>> {
        return localTaskDataSource.getAllTasks()
    }

    override suspend fun getTask(id: Int): Result<Task, DataError> {
        return localTaskDataSource.getTask(id)
    }

    override suspend fun storeTask(task: Task): EmptyDataResult<DataError> {
        return localTaskDataSource.upsertTask(task)
    }

    override suspend fun updateTask(task: Task): EmptyDataResult<DataError> {
        return localTaskDataSource.upsertTask(task)
    }

}