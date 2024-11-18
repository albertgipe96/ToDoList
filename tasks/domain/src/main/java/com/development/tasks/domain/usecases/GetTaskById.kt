package com.development.tasks.domain.usecases

import com.development.core.domain.model.Task
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result
import com.development.tasks.domain.TaskRepository
import javax.inject.Inject

class GetTaskById @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(id: Int): Result<Task, DataError> {
        return taskRepository.getTask(id)
    }
}