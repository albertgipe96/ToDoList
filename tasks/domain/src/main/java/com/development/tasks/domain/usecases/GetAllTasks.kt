package com.development.tasks.domain.usecases

import com.development.core.domain.model.Task
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result
import com.development.tasks.domain.TaskRepository
import javax.inject.Inject

class GetAllTasks @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): Result<List<Task>, DataError> {
        return taskRepository.getAllTasks()
    }
}