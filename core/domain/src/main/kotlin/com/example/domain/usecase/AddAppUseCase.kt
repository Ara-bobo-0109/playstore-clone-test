package com.example.domain.usecase

import com.example.domain.repo.AppRepository

class AddAppUseCase(private val repo: AppRepository) {
    suspend operator fun invoke(
        name: String,
        packageName: String,
        developer: String,
        description: String,
        iconSeed: Int,
    ): Long = repo.addApp(name, packageName, developer, description, iconSeed)
}
