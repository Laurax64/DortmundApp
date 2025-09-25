package com.example.dortmundapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.dortmundapp.R
import com.example.dortmundapp.data.Place

/**
 * Displays a Place with it's details
 */
@Composable
fun PlaceDetailsScreen(
    uiState: DortmundUiState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    isFullScreen: Boolean = false
) {
    BackHandler {
        onBackPressed()
    }
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .testTag(stringResource(R.string.details_screen))
                .fillMaxSize()
                .padding(top = dimensionResource(R.dimen.detail_card_list_padding_top))
        ) {
            item {
                if (isFullScreen) {
                    DetailsScreenTopBar(
                        onBackPressed,
                        uiState,
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = dimensionResource(R.dimen.detail_topbar_padding_bottom))
                    )
                }
                PlaceDetailsColumn(
                    place = uiState.currentSelectedPlace,
                    modifier = if (isFullScreen)
                        Modifier.padding(horizontal = dimensionResource(R.dimen.detail_card_outer_padding_horizontal))
                    else
                        Modifier.padding(end = dimensionResource(R.dimen.detail_card_outer_padding_horizontal))
                )
            }
        }
    }
}

/**
 * Displays the top bar of the place details screen
 */
@Composable
private fun DetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    uiState: DortmundUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.detail_topbar_back_button_padding_horizontal))
                .background(MaterialTheme.colorScheme.surface, shape = CircleShape),
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_arrow_back_24),
                contentDescription = stringResource(id = R.string.navigation_back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dimensionResource(R.dimen.detail_subject_padding_end))
        ) {
            Text(
                text = uiState.currentCategory.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


/**
 * Displays a places name, image and description
 */
@Composable
private fun PlaceDetailsColumn(
    place: Place,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.detail_vertical_padding))
    ) {
        Text(
            text = stringResource(place.name),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.outline
        )
        Image(
            painter = painterResource(place.painterResource),
            contentDescription = null,
        )
        Text(
            text = stringResource(place.details),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}




