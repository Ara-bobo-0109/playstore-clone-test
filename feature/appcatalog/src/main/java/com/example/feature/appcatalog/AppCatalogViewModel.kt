package com.example.feature.appcatalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AppItem
import com.example.domain.usecase.ObserveAppsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AppCatalogUiState(
    val query: String = "",
    val allApps: List<AppItem> = emptyList(),
    val filteredApps: List<AppItem> = emptyList(),
)

class AppCatalogViewModel(
    private val observeApps: ObserveAppsUseCase,
) : ViewModel() {

    private val query = MutableStateFlow("")
    private val apps = observeApps()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val uiState: StateFlow<AppCatalogUiState> =
        combine(query, apps) { q, list ->
            val normalized = q.trim()
            val filtered =
                if (normalized.isEmpty()) list
                else {
                    val needle = normalized.lowercase()
                    list.filter {
                        it.name.lowercase().contains(needle) ||
                            it.developer.lowercase().contains(needle) ||
                            it.packageName.lowercase().contains(needle)
                    }
                }
            AppCatalogUiState(
                query = q,
                allApps = list,
                filteredApps = filtered
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppCatalogUiState())

    fun onQueryChange(value: String) {
        viewModelScope.launch { query.emit(value) }
    }
}
