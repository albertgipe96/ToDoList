@file:OptIn(ExperimentalMaterial3Api::class)

package com.development.tasks.ui.internal.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.development.tasks.ui.R
import com.development.tasks.ui.internal.list.components.TaskCard

@Composable
internal fun ToDoListScreenInternal(
    viewModel: ToDoListViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToAddTask: () -> Unit
) {
    val uiState = viewModel.uiState
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.to_do_list_screen_title)) },
                actions = {
                    Icon(
                        modifier = Modifier.clickable { onNavigateToAddTask() },
                        painter = rememberVectorPainter(Icons.Default.Add),
                        contentDescription = stringResource(R.string.to_do_list_add_task_icon_content_description)
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(uiState.tasks) { task ->
                TaskCard(
                    title = task.title,
                    description = task.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToDetail(task.id) }
                        .padding(vertical = 4.dp),
                    isDone = task.isDone
                )
            }
        }
        if (uiState.loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}