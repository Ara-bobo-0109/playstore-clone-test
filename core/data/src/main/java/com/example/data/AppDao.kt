package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM applications WHERE name LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<AppEntity>>

    @Query("SELECT * FROM applications")
    fun getAll(): Flow<List<AppEntity>>

    @Query("SELECT * FROM applications WHERE id = :id")
    fun getById(id: Long): Flow<AppEntity>

    @Insert
    suspend fun insert(app: AppEntity)

    @Query("UPDATE applications SET isInstalled = NOT isInstalled WHERE id = :id")
    suspend fun toggleInstallation(id: Long)
}
