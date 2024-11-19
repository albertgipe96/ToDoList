package com.development.tasks.data.datasource

import com.development.core.domain.model.Task
import com.development.core.domain.result.DataError
import com.development.core.domain.result.Result
import com.development.core.storage.dao.TaskDao
import com.development.core.storage.model.TaskEntity
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RoomLocalTaskDataSourceTest {

    private val taskDao: TaskDao = mockk()

    private lateinit var roomLocalTaskDataSource: RoomLocalTaskDataSource

    @Before
    fun setup() {
        roomLocalTaskDataSource = RoomLocalTaskDataSource(taskDao)
    }

    @Test
    fun `GIVEN user has no tasks, WHEN retrieving task list, THEN returns empty list`() = runTest {
        // Given
        val taskEntityList = listOf(TaskEntity(id = 0, title = "title", description = "description", isDone = false))
        val taskList = taskEntityList.map { it.toTask() }
        coEvery { taskDao.getTaskList() } returns flowOf(taskEntityList)

        // When
        val result = roomLocalTaskDataSource.getAllTasks()

        // Then
        coVerify(exactly = 1) { taskDao.getTaskList() }
        assertEquals(result.first(), taskList)
    }

    @Test
    fun `GIVEN user has tasks, WHEN retrieving task by id, THEN returns result success with task`() = runTest {
        // Given
        val id = 0
        val taskEntity = TaskEntity(id = id, title = "title", description = "description", isDone = false)
        val taskResult = Result.Success(taskEntity.toTask())
        coEvery { taskDao.getTaskById(id) } returns listOf(taskEntity)

        // When
        val result = roomLocalTaskDataSource.getTask(id)

        // Then
        coVerify(exactly = 1) { taskDao.getTaskById(any()) }
        assertEquals(result, taskResult)
    }

    @Test
    fun `GIVEN user has tasks, WHEN retrieving task by id failed, THEN returns result error`() = runTest {
        // Given
        val id = 0
        val error = Result.Error(DataError.Local.NOT_FOUND)
        coEvery { taskDao.getTaskById(id) } throws Exception()

        // When
        val result = roomLocalTaskDataSource.getTask(id)

        // Then
        coVerify(exactly = 1) { taskDao.getTaskById(any()) }
        assertEquals(result, error)
    }

    @Test
    fun `GIVEN existing task, WHEN updating task correctly, THEN returns result success`() = runTest {
        // Given
        val task = Task(id = 0, title = "title", description = "description", isDone = false)
        val taskEntity = TaskEntity(id = 0, title = "title", description = "description", isDone = false)
        val success = Result.Success(Unit)
        coEvery { taskDao.upsertTask(taskEntity) } just Runs

        // When
        val result = roomLocalTaskDataSource.upsertTask(task)

        // Then
        coVerify(exactly = 1) { taskDao.upsertTask(any()) }
        assertEquals(result, success)
    }

    @Test
    fun `GIVEN existing task, WHEN updating task failed, THEN returns result error`() = runTest {
        // Given
        val task = Task(id = 0, title = "title", description = "description", isDone = false)
        val taskEntity = TaskEntity(id = 0, title = "title", description = "description", isDone = false)
        val error = Result.Error(DataError.Local.DISK_FULL)
        coEvery { taskDao.upsertTask(taskEntity) } throws Exception()

        // When
        val result = roomLocalTaskDataSource.upsertTask(task)

        // Then
        coVerify(exactly = 1) { taskDao.upsertTask(any()) }
        assertEquals(result, error)
    }



}