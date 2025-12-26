package com.example.application

import android.content.Context
import androidx.room.Room
import com.example.data.db.AppDatabase
import com.example.data.repo.RoomAppRepository
import com.example.domain.repo.AppRepository
import com.example.domain.usecase.*

class AppGraph(context: Context) {
    private val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "playstoreclone.db"
    ).build()

    val repo: AppRepository = RoomAppRepository(db.appDao())

    val observeApps = ObserveAppsUseCase(repo)
    val observeDetail = ObserveAppDetailUseCase(repo)
    val addApp = AddAppUseCase(repo)
    val setInstalled = SetInstalledUseCase(repo)
}
