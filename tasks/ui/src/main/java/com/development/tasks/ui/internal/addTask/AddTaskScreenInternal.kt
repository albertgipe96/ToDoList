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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.development.core.ui.ObserveAsEvents

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
                Toast.makeText(context, "ADDING TASK FAILED WITH ERROR: ${event.error}", Toast.LENGTH_LONG).show()
            }
            AddTaskEvent.OnProcessFinishedCorrectly -> {
                Toast.makeText(context, "TASK ADDED CORRECTLY", Toast.LENGTH_LONG).show()
                onProcessFinished()
            }
        }
    }

    val uiState = viewModel.uiState
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Add task") },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { onNavigateBack() },
                        painter = rememberVectorPainter(Icons.AutoMirrored.Filled.ArrowBack),
                        contentDescription = "Navigate back"
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
            Column(modifier = Modifier.fillMaxWidth().weight(1F, false)) {
                TextField(
                    value = uiState.task.title,
                    onValueChange = { newValue ->
                        viewModel.onAction(AddTaskAction.OnTitleTextValueChanged(newValue))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Title")
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
                        Text(text = "Description")
                    }
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.loading && uiState.task.title.isNotEmpty(),
                onClick = { viewModel.onAction(AddTaskAction.OnSaveTask) }
            ) {
                Text(text = "SAVE TASK")
            }
        }
        if (uiState.loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}