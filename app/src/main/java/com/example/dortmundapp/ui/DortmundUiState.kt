package com.example.dortmundapp.ui

import com.example.dortmundapp.data.Category
import com.example.dortmundapp.data.LocalPlacesDataProvider
import com.example.dortmundapp.data.Place

/**
 * Holds the UI state of the app
 */
data class DortmundUiState(
    val categories: Map<Category, List<Place>> = emptyMap(),
    val currentCategory: Category = Category.Parks,
    val currentSelectedPlace: Place = LocalPlacesDataProvider.defaultPlace,
    val isShowingRecommendations: Boolean = true
) {
    val currentCategoryPlaces: List<Place> by lazy { categories[currentCategory] ?: emptyList() }
}
