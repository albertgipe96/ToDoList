package com.development.tasks.ui.internal.list

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.development.core.domain.model.Task
import com.development.core.domain.result.DataError
import com.development.core.domain.result.onError
import com.development.core.domain.result.onSuccess
import com.development.tasks.domain.TaskRepository
import com.development.tasks.domain.usecases.GetAllTasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

internal data class ToDoListUiState(
    val loading: Boolean = true,
    val tasks: List<Task> = emptyList(),
    val error: DataError? = null
)

@HiltViewModel
@Stable
internal class ToDoListViewModel @Inject constructor(
    private val getAllTasks: GetAllTasks
) : ViewModel() {

    var uiState by mutableStateOf(ToDoListUiState())
        private set

    init {
       loadTasksData()
    }

    private fun loadTasksData() {
        viewModelScope.launch {
            getAllTasks()
                .onSuccess { tasks ->
                    uiState = uiState.copy(
                        loading = false,
                        tasks = tasks
                    )
                }
                .onError { error ->
                    uiState = uiState.copy(
                        loading = false,
                        error = error
                    )
                }
        }
    }

}