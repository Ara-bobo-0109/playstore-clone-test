package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "applications")
data class AppEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val developerName: String,
    val category: String,
    val rating: Float?,
    val description: String?,
    val iconColor: Long,
    val isInstalled: Boolean
)
