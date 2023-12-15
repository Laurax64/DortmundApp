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
package com.example.reply.ui

import androidx.lifecycle.ViewModel
import com.example.dortmundapp.data.Category
import com.example.dortmundapp.data.LocalPlacesDataProvider
import com.example.dortmundapp.data.Place
import com.example.dortmundapp.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        val categories: Map<Category, List<Place>> =
            LocalPlacesDataProvider.allPlaces.groupBy { it.category }
        _uiState.value =
            UiState(
                categories = categories,
                currentSelectedPlace = categories[Category.Park]?.get(0)
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