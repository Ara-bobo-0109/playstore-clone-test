package com.example.feature.addapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AddAppUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

data class AddApplicationUiState(
    val name: String = "",
    val packageName: String = "",
    val developer: String = "",
    val description: String = "",
    val error: String? = null,
    val savedId: Long? = null,
)

class AddApplicationViewModel(
    private val addApp: AddAppUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AddApplicationUiState())
    val state: StateFlow<AddApplicationUiState> = _state

    fun onName(v: String) = update { copy(name = v, error = null) }
    fun onPackage(v: String) = update { copy(packageName = v, error = null) }
    fun onDeveloper(v: String) = update { copy(developer = v, error = null) }
    fun onDescription(v: String) = update { copy(description = v, error = null) }

    fun save() {
        val s = _state.value
        val name = s.name.trim()
        val pkg = s.packageName.trim()
        val dev = s.developer.trim()
        val desc = s.description.trim()

        if (name.isBlank()) return update { copy(error = "Name is required") }
        if (pkg.isBlank() || !pkg.contains(".")) return update { copy(error = "Package name must look like com.example.app") }
        if (dev.isBlank()) return update { copy(error = "Developer is required") }

        val iconSeed = (name + "|" + pkg).hashCode() xor Random((name + pkg).hashCode()).nextInt()

        viewModelScope.launch {
            val id = addApp(
                name = name,
                packageName = pkg,
                developer = dev,
                description = desc,
                iconSeed = iconSeed
            )
            update { copy(savedId = id) }
        }
    }

    private fun update(block: AddApplicationUiState.() -> AddApplicationUiState) {
        _state.value = _state.value.block()
    }
}
