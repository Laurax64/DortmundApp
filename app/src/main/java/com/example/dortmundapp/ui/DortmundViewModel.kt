package com.example.dortmundapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Provides the current [DortmundUiState] by using a data holder observable flow [_uiState]
 * that emits the current and new state updates
 */
class DortmundViewModel : ViewModel() {

    // UI state is updated by changing the value property
    private val _uiState = MutableStateFlow(value = DortmundUiState())
    // Read only UI state for the UI
    val uiState: StateFlow<DortmundUiState> = _uiState.asStateFlow()
}