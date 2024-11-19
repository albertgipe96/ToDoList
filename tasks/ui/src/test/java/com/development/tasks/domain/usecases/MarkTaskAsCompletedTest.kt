package com.development.tasks.domain.usecases

import com.development.core.domain.model.Task
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result
import com.development.tasks.domain.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MarkTaskAsCompletedTest {

    private val taskRepository: TaskRepository = mockk()

    private lateinit var markTaskAsCompleted: MarkTaskAsCompleted

    @Before
    fun setup() {
        markTaskAsCompleted = MarkTaskAsCompleted(taskRepository)
    }

    @Test
    fun `GIVEN task not marked as completed, WHEN updating correctly, THEN returns task updated`() = runTest {
        // Given
        val taskNotCompleted = Task(id = 0, title = "title", description = "description", isDone = false)
        val taskCompleted = taskNotCompleted.copy(isDone = true)
        val taskSuccess = Result.Success(taskCompleted)
        coEvery { taskRepository.updateTask(taskCompleted) } returns Result.Success(Unit)

        // When
        val result = markTaskAsCompleted(taskNotCompleted)

        // Then
        coVerify(exactly = 1) { taskRepository.updateTask(any()) }
        assertEquals(result, taskSuccess)
    }

    @Test
    fun `GIVEN task not marked as completed, WHEN updating failed, THEN returns data error`() = runTest {
        // Given
        val taskNotCompleted = Task(id = 0, title = "title", description = "description", isDone = false)
        val taskCompleted = taskNotCompleted.copy(isDone = true)
        val error = Result.Error(DataError.Local.DISK_FULL)
        coEvery { taskRepository.updateTask(taskCompleted) } returns error

        // When
        val result = markTaskAsCompleted(taskNotCompleted)

        // Then
        coVerify(exactly = 1) { taskRepository.updateTask(any()) }
        assertEquals(result, error)
    }

}