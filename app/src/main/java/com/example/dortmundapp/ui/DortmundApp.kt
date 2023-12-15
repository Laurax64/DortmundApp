package com.example.dortmundapp.ui


import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dortmundapp.data.Category
import com.example.dortmundapp.data.Place
import com.example.dortmundapp.utils.ContentType
import com.example.dortmundapp.utils.NavigationType
import com.example.reply.ui.ViewModel


/**
 * Displays the first app screen adapted to the screen size
 */
@Composable
fun DortmundApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val navigationType: NavigationType
    val contentType: ContentType
    val viewModel: ViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.RECOMMENDATIONS_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = NavigationType.NAVIGATION_RAIL
            contentType = ContentType.RECOMMENDATIONS_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ContentType.RECOMMENDATIONS_AND_DETAIL
        }
        else -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.RECOMMENDATIONS_ONLY
        }
    }
    RecommendationScreen(
        navigationType = navigationType,
        contentType = contentType,
        uiState = uiState,
        onTabPressed = { category: Category ->
            viewModel.updateCurrentCategory(category = category)
            viewModel.resetHomeScreenStates()
        },
        onPlaceCardPressed = { place: Place ->
            viewModel.updateDetailsScreenStates(
                place = place
            )
        },
        onDetailScreenBackPressed = {
            viewModel.resetHomeScreenStates()
        },
        modifier = modifier
    )
}
