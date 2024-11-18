package com.development.tasks.ui.api

import androidx.compose.runtime.Composable
import com.development.tasks.ui.internal.addTask.AddTaskScreenInternal
import com.development.tasks.ui.internal.list.ToDoListScreenInternal

@Composable
fun AddTaskScreen(
    onProcessFinished: () -> Unit
) {
    AddTaskScreenInternal(
        onProcessFinished = onProcessFinished
    )
}