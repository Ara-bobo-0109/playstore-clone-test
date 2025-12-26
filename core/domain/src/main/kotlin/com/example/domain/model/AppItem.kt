package com.example.domain.model

data class AppItem(
    val id: Long,
    val name: String,
    val packageName: String,
    val developer: String,
    val description: String,
    val installed: Boolean,
    val iconSeed: Int,
    val createdAtEpochMs: Long,
)
