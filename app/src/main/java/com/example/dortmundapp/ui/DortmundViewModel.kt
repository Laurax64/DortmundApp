package com.example.dortmundapp.ui

import androidx.lifecycle.ViewModel
import com.example.dortmundapp.data.LocalCategoryDataProvider
import com.example.dortmundapp.model.Category
import com.example.dortmundapp.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Provides the current [DortmundUiState] by using a data holder observable flow [_uiState]
 * that emits the current and new state updates
 */
class DortmundViewModel : ViewModel() {

    // UI state is updated by changing the value property
    private val _uiState = MutableStateFlow(value = DortmundUiState(
        categoryList = LocalCategoryDataProvider.getCategoryData(),
        currentCategory = LocalCategoryDataProvider.defaultCategory,
        currentPlace = LocalCategoryDataProvider.defaultPlace)
    )
    // Read only UI state for the UI
    val uiState: StateFlow<DortmundUiState> = _uiState.asStateFlow()

    /**
     * Updates currentCategory and sets the new default place in [_uiState]
     */
    fun updateCurrentCategory(selectedCategory: Category) {
        _uiState.update {
            it.copy(currentCategory = selectedCategory, currentPlace = selectedCategory.places[0])
        }
    }

    /**
     * Updates currentPlace in [_uiState]
     */
    fun updateCurrentPlace(selectedPlace: Place) {
        _uiState.update {
            it.copy( currentPlace = selectedPlace)
        }
    }

}