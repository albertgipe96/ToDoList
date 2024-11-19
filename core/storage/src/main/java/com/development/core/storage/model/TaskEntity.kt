package com.development.core.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.development.core.domain.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val isDone: Boolean
) {
    fun toTask(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            isDone = isDone
        )
    }
}