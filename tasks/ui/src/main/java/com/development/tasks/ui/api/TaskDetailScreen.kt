package com.development.tasks.ui.api

import androidx.compose.runtime.Composable
import com.development.tasks.ui.internal.detail.TaskDetailScreenInternal

@Composable
fun TaskDetailScreen(
    onNavigateBack: () -> Unit
) {
    TaskDetailScreenInternal(
        onNavigateBack = onNavigateBack
    )
}