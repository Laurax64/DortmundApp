package com.example.dortmundapp.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Main composable that serves as container which displays
 * content according to [DortmundViewModel.uiState] and [windowSize]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DortmundApp(windowSize: WindowWidthSizeClass) {
    val viewModel: DortmundViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

}