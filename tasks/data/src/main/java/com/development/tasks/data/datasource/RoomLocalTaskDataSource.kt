package com.development.tasks.data.datasource

import com.development.core.domain.result.DataError
import com.development.core.domain.result.EmptyDataResult
import com.development.core.domain.result.Result
import com.development.core.domain.model.Task
import com.development.core.storage.dao.TaskDao
import com.development.core.storage.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RoomLocalTaskDataSource @Inject constructor(
    private val taskDao: TaskDao
) : LocalTaskDataSource {

    override suspend fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getTaskList().map { it.map { it.toTask() } }
    }

    override suspend fun getTask(id: Int): Result<Task, DataError> {
        return try {
            val tasks = taskDao.getTaskById(id).first().toTask()
            Result.Success(tasks)
        } catch (e: Exception) {
            Result.Error(DataError.Local.NOT_FOUND)
        }
    }

    override suspend fun upsertTask(task: Task): EmptyDataResult<DataError> {
        return try {
            taskDao.upsertTask(task.toTaskEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    private fun Task.toTaskEntity(): TaskEntity {
        return TaskEntity(
            id = id,
            title = title,
            description = description,
            isDone = isDone
        )
    }

}