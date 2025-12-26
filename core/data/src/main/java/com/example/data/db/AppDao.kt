package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM apps ORDER BY name COLLATE NOCASE ASC")
    fun observeAll(): Flow<List<AppEntity>>

    @Query("SELECT * FROM apps WHERE id = :id LIMIT 1")
    fun observeById(id: Long): Flow<AppEntity?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: AppEntity): Long

    @Query("UPDATE apps SET installed = :installed WHERE id = :id")
    suspend fun setInstalled(id: Long, installed: Boolean)

    @Query("SELECT COUNT(*) FROM apps")
    suspend fun count(): Int
}
