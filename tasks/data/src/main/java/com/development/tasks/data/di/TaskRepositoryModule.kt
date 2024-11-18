package com.development.tasks.data.di

import com.development.core.storage.dao.TaskDao
import com.development.tasks.data.TaskRepositoryImpl
import com.development.tasks.data.datasource.LocalTaskDataSource
import com.development.tasks.data.datasource.RoomLocalTaskDataSource
import com.development.tasks.domain.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TaskRepositoryModule {
    @Provides
    @ViewModelScoped
    fun providesTaskRepository(
        localTaskDataSource: LocalTaskDataSource
    ): TaskRepository {
        return TaskRepositoryImpl(localTaskDataSource)
    }

    @Provides
    @ViewModelScoped
    fun providesLocalTaskDataSource(
        taskDao: TaskDao
    ): LocalTaskDataSource {
        return RoomLocalTaskDataSource(taskDao)
    }
}