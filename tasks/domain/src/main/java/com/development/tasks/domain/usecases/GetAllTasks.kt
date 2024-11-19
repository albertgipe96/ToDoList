package com.development.tasks.domain.usecases

import com.development.core.domain.model.Task
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result
import com.development.tasks.domain.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasks @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): Flow<List<Task>> {
        return taskRepository.getAllTasks()
    }
}