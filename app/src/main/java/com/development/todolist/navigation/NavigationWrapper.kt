package com.development.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.development.tasks.ui.api.AddTaskScreen
import com.development.tasks.ui.api.TaskDetailScreen
import com.development.tasks.ui.api.ToDoListScreen
import com.development.core.navigation.NavRoute.List
import com.development.core.navigation.NavRoute.Detail
import com.development.core.navigation.NavRoute.AddTask

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = List
    ) {
        composable<List> {
            ToDoListScreen(
                onNavigateToDetail = { taskId ->
                    navController.navigate(Detail(taskId))
                },
                onNavigateToAddTask = {
                    navController.navigate(AddTask)
                }
            )
        }
        composable<Detail> {
            TaskDetailScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<AddTask> {
            AddTaskScreen(
                onProcessFinished = {
                    navController.navigateUp()
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}