package com.development.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.development.todolist.navigation.NavRoute.List
import com.development.todolist.navigation.NavRoute.Detail
import com.development.todolist.navigation.NavRoute.AddTask

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = List
    ) {
        composable<List> {

        }
        composable<Detail> {

        }
        composable<AddTask> {

        }
    }
}