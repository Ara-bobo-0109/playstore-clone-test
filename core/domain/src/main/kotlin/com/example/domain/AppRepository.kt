package com.example.domain

import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getApplications(query: String): Flow<List<AppMetadata>>
    fun getApplication(id: Long): Flow<AppMetadata>
    suspend fun addApplication(app: AppMetadata)
    suspend fun toggleInstallation(id: Long)
}
