package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps")
data class AppEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val packageName: String,
    val developer: String,
    val description: String,
    val installed: Boolean,
    val iconSeed: Int,
    val createdAtEpochMs: Long,
)
