package com.development.tasks.ui.internal.di

import com.development.tasks.domain.TaskRepository
import com.development.tasks.domain.usecases.MarkTaskAsCompleted
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

}