package com.development.tasks.ui.internal.detail

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.development.core.domain.model.Task
import com.development.core.domain.result.DataError
import com.development.core.domain.result.onError
import com.development.core.domain.result.onSuccess
import com.development.core.navigation.NavRoute
import com.development.tasks.domain.usecases.GetTaskById
import com.development.tasks.domain.usecases.MarkTaskAsCompleted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

internal data class TaskDetailUiState(
    val loading: Boolean = true,
    val detailState: TaskState = TaskState.Idle
)

internal sealed interface TaskState {
    data object Idle : TaskState
    data class TaskLoaded(val task: Task) : TaskState
    data class Error(val error: DataError) : TaskState
}

internal sealed interface TaskDetailAction {
    data class MarkAsCompleted(val task: Task) : TaskDetailAction
}

@HiltViewModel
@Stable
internal class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTaskById: GetTaskById,
    private val markTaskAsCompleted: MarkTaskAsCompleted
) : ViewModel() {

    private val taskId: Int = savedStateHandle.toRoute<NavRoute.Detail>().taskId

    var uiState by mutableStateOf(TaskDetailUiState())
        private set

    init {
        loadTaskDetailData()
    }

    fun onAction(action: TaskDetailAction) {
        when (action) {
            is TaskDetailAction.MarkAsCompleted -> handleMarkAsCompleted(action.task)
        }
    }

    private fun loadTaskDetailData() {
        viewModelScope.launch {
            getTaskById(taskId)
                .onSuccess { task ->
                    uiState = uiState.copy(
                        loading = false,
                        detailState = TaskState.TaskLoaded(task)
                    )
                }
                .onError { error ->
                    uiState = uiState.copy(
                        loading = false,
                        detailState = TaskState.Error(error)
                    )
                }
        }
    }

    private fun handleMarkAsCompleted(task: Task) {
        viewModelScope.launch {
            markTaskAsCompleted(task)
                .onSuccess { taskUpdated ->
                    uiState = uiState.copy(
                        loading = false,
                        detailState = TaskState.TaskLoaded(taskUpdated)
                    )
                }
                .onError { error ->
                    uiState = uiState.copy(
                        loading = false,
                        detailState = TaskState.Error(error)
                    )
                }
        }
    }

}