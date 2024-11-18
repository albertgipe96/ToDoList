package com.development.tasks.ui.api

import androidx.compose.runtime.Composable
import com.development.tasks.ui.internal.list.ToDoListScreenInternal

@Composable
fun ToDoListScreen(
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToAddTask: () -> Unit
) {
    ToDoListScreenInternal(
        onNavigateToDetail = onNavigateToDetail,
        onNavigateToAddTask = onNavigateToAddTask
    )
}