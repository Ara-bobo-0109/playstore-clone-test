package com.example.data.mappers

import com.example.data.db.AppEntity
import com.example.domain.model.AppItem

fun AppEntity.toDomain(): AppItem = AppItem(
    id = id,
    name = name,
    packageName = packageName,
    developer = developer,
    description = description,
    installed = installed,
    iconSeed = iconSeed,
    createdAtEpochMs = createdAtEpochMs,
)
