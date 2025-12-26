package com.example.domain.usecase

import com.example.domain.repo.AppRepository

class SetInstalledUseCase(private val repo: AppRepository) {
    suspend operator fun invoke(id: Long, installed: Boolean) = repo.setInstalled(id, installed)
}
