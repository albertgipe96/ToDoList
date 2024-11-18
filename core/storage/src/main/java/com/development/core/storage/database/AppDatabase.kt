package com.development.core.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.development.core.storage.dao.TaskDao
import com.development.core.storage.model.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
}