package com.development.tasks.ui.internal.di

import com.development.tasks.domain.TaskRepository
import com.development.tasks.domain.usecases.GetAllTasks
import com.development.tasks.domain.usecases.GetTaskById
import com.development.tasks.domain.usecases.MarkTaskAsCompleted
import com.development.tasks.domain.usecases.StoreNewTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class TasksUseCasesModule {

    @Provides
    fun providesMarkTaskAsCompletedUseCase(
        taskRepository: TaskRepository
    ): MarkTaskAsCompleted {
        return MarkTaskAsCompleted(taskRepository)
    }

    @Provides
    fun providesGetTaskByIdUseCase(
        taskRepository: TaskRepository
    ): GetTaskById {
        return GetTaskById(taskRepository)
    }

    @Provides
    fun providesStoreNewTaskUseCase(
        taskRepository: TaskRepository
    ): StoreNewTask {
        return StoreNewTask(taskRepository)
    }

    @Provides
    fun providesGetAllTasksUseCase(
        taskRepository: TaskRepository
    ): GetAllTasks {
        return GetAllTasks(taskRepository)
    }

}