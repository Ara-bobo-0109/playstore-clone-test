package com.example.data.repo

import com.example.data.db.AppDao
import com.example.data.db.AppEntity
import com.example.data.mappers.toDomain
import com.example.domain.model.AppItem
import com.example.domain.repo.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomAppRepository(private val dao: AppDao) : AppRepository {

    override fun observeAllApps(): Flow<List<AppItem>> =
        dao.observeAll().map { list -> list.map { it.toDomain() } }

    override fun observeAppById(id: Long): Flow<AppItem?> =
        dao.observeById(id).map { it?.toDomain() }

    override suspend fun addApp(
        name: String,
        packageName: String,
        developer: String,
        description: String,
        iconSeed: Int,
    ): Long {
        val now = System.currentTimeMillis()
        return dao.insert(
            AppEntity(
                name = name.trim(),
                packageName = packageName.trim(),
                developer = developer.trim(),
                description = description.trim(),
                installed = false,
                iconSeed = iconSeed,
                createdAtEpochMs = now,
            )
        )
    }

    override suspend fun setInstalled(id: Long, installed: Boolean) {
        dao.setInstalled(id, installed)
    }
}
