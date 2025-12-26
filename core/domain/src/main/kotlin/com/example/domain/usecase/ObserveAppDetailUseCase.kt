package com.example.domain.usecase

import com.example.domain.model.AppItem
import com.example.domain.repo.AppRepository
import kotlinx.coroutines.flow.Flow

class ObserveAppDetailUseCase(private val repo: AppRepository) {
    operator fun invoke(id: Long): Flow<AppItem?> = repo.observeAppById(id)
}
