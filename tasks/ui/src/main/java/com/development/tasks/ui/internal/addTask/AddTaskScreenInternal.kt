@file:OptIn(ExperimentalMaterial3Api::class)

package com.development.tasks.ui.internal.addTask

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.development.core.ui.ObserveAsEvents
import com.development.tasks.ui.R

@Composable
internal fun AddTaskScreenInternal(
    viewModel: AddTaskViewModel = hiltViewModel(),
    onProcessFinished: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    ObserveAsEvents(viewModel.eventsFlow) { event ->
        when (event) {
            is AddTaskEvent.OnProcessError -> {
                Toast.makeText(context, context.getString(R.string.adding_task_failed_with_error_toast, event.error), Toast.LENGTH_LONG).show()
            }
            AddTaskEvent.OnProcessFinishedCorrectly -> {
                Toast.makeText(context, context.getString(R.string.task_added_correctly_toast), Toast.LENGTH_LONG).show()
                onProcessFinished()
            }
        }
    }

    val uiState = viewModel.uiState
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.add_task_screen_title)) },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { onNavigateBack() },
                        painter = rememberVectorPainter(Icons.AutoMirrored.Filled.ArrowBack),
                        contentDescription = stringResource(R.string.add_task_navigate_back_content_description)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1F, false)) {
                TextField(
                    value = uiState.task.title,
                    onValueChange = { newValue ->
                        viewModel.onAction(AddTaskAction.OnTitleTextValueChanged(newValue))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(R.string.add_task_text_field_title_label))
                    }
                )
                Spacer(Modifier.height(16.dp))
                TextField(
                    value = uiState.task.description,
                    onValueChange = { newValue ->
                        viewModel.onAction(AddTaskAction.OnDescriptionTextValueChanged(newValue))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(R.string.add_task_text_field_description_label))
                    }
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.loading && uiState.task.title.isNotEmpty(),
                onClick = { viewModel.onAction(AddTaskAction.OnSaveTask) }
            ) {
                Text(text = stringResource(R.string.add_task_save_task_button))
            }
        }
        if (uiState.loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}