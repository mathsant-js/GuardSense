package com.example.guardsense.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guardsense.data.repository.SensorRepository
import kotlinx.coroutines.launch

sealed class SensorState {
    object Idle : SensorState()
    object Loading : SensorState()
    object Success : SensorState()
    data class Error(val message: String) : SensorState()
}

class SensorViewModel(
    private val repository: SensorRepository = SensorRepository()
) : ViewModel() {

    var uiState by mutableStateOf<SensorState>(SensorState.Idle)
        private set

    fun updateKeypadCode(newCode: String) {
        viewModelScope.launch {
            uiState = SensorState.Loading
            val result = repository.updateKeypadCode(newCode)
            uiState = if (result.isSuccess) {
                SensorState.Success
            } else {
                SensorState.Error(result.exceptionOrNull()?.message ?: "Erro ao atualizar o código.")
            }
        }
    }

    fun resetState() {
        uiState = SensorState.Idle
    }
}