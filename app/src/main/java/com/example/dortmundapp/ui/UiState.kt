/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.dortmundapp.ui

import com.example.dortmundapp.data.Category
import com.example.dortmundapp.data.LocalPlacesDataProvider
import com.example.dortmundapp.data.Place

/**
 * Holds the UI state of the app
 */
data class UiState(
    val categories: Map<Category, List<Place>> = emptyMap(),
    val currentCategory: Category = Category.Parks,
    val currentSelectedPlace: Place = LocalPlacesDataProvider.defaultPlace,
    val isShowingRecommendations: Boolean = true
) {
    val currentCategoryPlaces: List<Place> by lazy { categories[currentCategory] ?: emptyList() }
}
