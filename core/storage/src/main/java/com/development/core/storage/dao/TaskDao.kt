package com.development.core.storage.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.development.core.storage.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTaskList(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id=:id")
    suspend fun getTaskById(id: Int): List<TaskEntity>

    @Upsert
    suspend fun upsertTask(product: TaskEntity)

}