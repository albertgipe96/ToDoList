package com.development.core.domain.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isDone: Boolean
)