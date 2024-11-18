package com.development.todolist.navigation

import kotlinx.serialization.Serializable

sealed interface NavRoute {
    @Serializable data object List : NavRoute
    @Serializable data object Detail : NavRoute
    @Serializable data object AddTask : NavRoute
}