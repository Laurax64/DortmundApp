package com.example.dortmundapp.ui

import androidx.lifecycle.ViewModel
import com.example.dortmundapp.data.Category
import com.example.dortmundapp.data.LocalPlacesDataProvider
import com.example.dortmundapp.data.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DortmundViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DortmundUiState())
    val uiState: StateFlow<DortmundUiState> = _uiState

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        val categories: Map<Category, List<Place>> =
            LocalPlacesDataProvider.allPlaces.groupBy { it.category }
        _uiState.value =
            DortmundUiState(
                categories = categories,
                currentSelectedPlace = categories[Category.Parks]?.get(0)
                    ?: LocalPlacesDataProvider.defaultPlace
            )
    }

    fun updateDetailsScreenStates(place: Place) {
        _uiState.update {
            it.copy(
                currentSelectedPlace = place,
                isShowingRecommendations = false
            )
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedPlace = it.categories[it.currentCategory]?.get(0)
                    ?: LocalPlacesDataProvider.defaultPlace,
                isShowingRecommendations = true
            )
        }
    }

    fun updateCurrentCategory(category: Category) {
        _uiState.update {
            it.copy(
                currentCategory = category
            )
        }
    }
}