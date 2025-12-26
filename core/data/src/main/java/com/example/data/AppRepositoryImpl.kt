package com.example.data

import com.example.domain.AppMetadata
import com.example.domain.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepositoryImpl(
    private val dao: AppDao
) : AppRepository {

    override fun getApplications(query: String): Flow<List<AppMetadata>> =
        (if (query.isBlank()) {
            dao.getAll()
        } else {
            dao.search(query)
        }).map { list ->
            list.map { it.toDomain() }
        }

    override fun getApplication(id: Long): Flow<AppMetadata> =
        dao.getById(id).map { it.toDomain() }

    override suspend fun addApplication(app: AppMetadata) {
        dao.insert(app.toEntity())
    }

    override suspend fun toggleInstallation(id: Long) {
        dao.toggleInstallation(id)
    }
}

private fun AppEntity.toDomain() = AppMetadata(
    id = id,
    name = name,
    developerName = developerName,
    category = category,
    rating = rating,
    description = description,
    iconColor = iconColor,
    isInstalled = isInstalled
)

private fun AppMetadata.toEntity() = AppEntity(
    id = id,
    name = name,
    developerName = developerName,
    category = category,
    rating = rating,
    description = description,
    iconColor = iconColor,
    isInstalled = isInstalled
)
