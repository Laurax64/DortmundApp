package com.example.dortmundapp.ui

import com.example.dortmundapp.data.LocalCategoryDataProvider
import com.example.dortmundapp.model.Category
import com.example.dortmundapp.model.Place

/**
 * Holds the UI state of the app
 */
data class DortmundUiState(
    val categoryList: List<Category>,
    val currentCategory: Category = LocalCategoryDataProvider.defaultCategory,
    val currentPlace: Place = LocalCategoryDataProvider.defaultPlace,
    val isShowingCategoryListPage: Boolean = true,
    val isShowingPlaceListPage: Boolean = true
)
