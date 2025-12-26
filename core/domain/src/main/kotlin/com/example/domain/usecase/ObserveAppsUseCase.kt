package com.example.domain.usecase

import com.example.domain.model.AppItem
import com.example.domain.repo.AppRepository
import kotlinx.coroutines.flow.Flow

class ObserveAppsUseCase(private val repo: AppRepository) {
    operator fun invoke(): Flow<List<AppItem>> = repo.observeAllApps()
}
