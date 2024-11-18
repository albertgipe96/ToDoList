package com.development.tasks.ui.internal.addTask

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
import com.development.tasks.domain.usecases.StoreNewTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

data class AddTaskUiState(
    val loading: Boolean = false,
    val task: Task = Task(id = Random.nextInt(), title = "", description = "", isDone = false)
)

sealed interface AddTaskAction {
    data class OnTitleTextValueChanged(val title: String) : AddTaskAction
    data class OnDescriptionTextValueChanged(val description: String) : AddTaskAction
    data object OnSaveTask : AddTaskAction
}

sealed interface AddTaskEvent {
    data object OnProcessFinishedCorrectly : AddTaskEvent
    data class OnProcessError(val error: DataError) : AddTaskEvent
}

@HiltViewModel
@Stable
internal class AddTaskViewModel @Inject constructor(
    private val storeNewTask: StoreNewTask
) : ViewModel() {

    var uiState by mutableStateOf(AddTaskUiState())
        private set

    private val eventChannel = Channel<AddTaskEvent>()
    val eventsFlow = eventChannel.receiveAsFlow()

    fun onAction(action: AddTaskAction) {
        when (action) {
            is AddTaskAction.OnTitleTextValueChanged -> updateTitle(action.title)
            is AddTaskAction.OnDescriptionTextValueChanged -> updateDescription(action.description)
            is AddTaskAction.OnSaveTask -> saveTask()
        }
    }

    private fun updateTitle(title: String) {
        uiState = uiState.copy(
            task = uiState.task.copy(title = title)
        )
    }

    private fun updateDescription(description: String) {
        uiState = uiState.copy(
            task = uiState.task.copy(description = description)
        )
    }

    private fun saveTask() {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)
            storeNewTask(uiState.task)
                .onSuccess {
                    eventChannel.send(AddTaskEvent.OnProcessFinishedCorrectly)
                    uiState = uiState.copy(loading = false)
                }
                .onError { error ->
                    eventChannel.send(AddTaskEvent.OnProcessError(error))
                    uiState = uiState.copy(loading = false)
                }
        }
    }

}