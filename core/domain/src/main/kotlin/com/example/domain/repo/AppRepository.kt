package com.example.domain.repo

import com.example.domain.model.AppItem
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun observeAllApps(): Flow<List<AppItem>>
    fun observeAppById(id: Long): Flow<AppItem?>
    suspend fun addApp(
        name: String,
        packageName: String,
        developer: String,
        description: String,
        iconSeed: Int,
    ): Long

    suspend fun setInstalled(id: Long, installed: Boolean)
}
