package com.example.feature.applicationdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AppItem
import com.example.domain.usecase.ObserveAppDetailUseCase
import com.example.domain.usecase.SetInstalledUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class ApplicationDetailUiState(
    val app: AppItem? = null
)

class ApplicationDetailViewModel(
    appId: Long,
    observeDetail: ObserveAppDetailUseCase,
    private val setInstalled: SetInstalledUseCase,
) : ViewModel() {

    val state: StateFlow<ApplicationDetailUiState> =
        observeDetail(appId)
            .map { ApplicationDetailUiState(app = it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ApplicationDetailUiState())

    fun toggleInstall() {
        val app = state.value.app ?: return
        viewModelScope.launch {
            setInstalled(app.id, !app.installed)
        }
    }
}
