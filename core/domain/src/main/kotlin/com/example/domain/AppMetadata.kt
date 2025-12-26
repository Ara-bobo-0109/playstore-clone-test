package com.example.domain

data class AppMetadata(
    val id: Long,
    val name: String,
    val developerName: String,
    val category: String,
    val rating: Float?,
    val description: String?,
    val iconColor: Long,
    val isInstalled: Boolean
)
