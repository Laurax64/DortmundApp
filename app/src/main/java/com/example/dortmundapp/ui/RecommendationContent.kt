package com.example.dortmundapp.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.dortmundapp.R
import com.example.dortmundapp.data.Place


@Composable
fun ListOnlyContent(
    uiState: UiState,
    onPlaceCardPressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val places = uiState.currentCategoryPlaces

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.email_list_item_vertical_spacing)
        )
    ) {
        item {
            ReplyHomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.topbar_padding_vertical))
            )
        }
        items(places, key = { place -> place.id }) { place ->
            PlaceListItem(
                place = place,
                selected = false,
                onCardClick = {
                    onPlaceCardPressed(place)
                }
            )
        }
    }
}

/**
 * Displays list and detail content
 */
@Composable
fun ListAndDetailContent(
    uiState: UiState,
    onPlaceCardPressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val places = uiState.currentCategoryPlaces
    Row(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(
                    end = dimensionResource(R.dimen.list_and_detail_list_padding_end),
                    top = dimensionResource(R.dimen.list_and_detail_list_padding_top)
                ),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.email_list_item_vertical_spacing)
            )
        ) {
            items(places, key = { place -> place.id }) { place ->
                PlaceListItem(
                    place = place,
                    selected = uiState.currentSelectedPlace.id == place.id,
                    onCardClick = {
                        onPlaceCardPressed(place)
                    },
                )
            }
        }
        val activity = LocalContext.current as Activity
        PlaceDetailsScreen(
            uiState = uiState,
            modifier = Modifier.weight(1f),
            onBackPressed = { activity.finish() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListItem(
    place: Place,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.email_list_item_inner_padding))
        ) {

            Text(
                text = stringResource(place.name),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.email_list_item_header_subject_spacing),
                    bottom = dimensionResource(R.dimen.email_list_item_subject_body_spacing)
                ),
            )
            Text(
                text = stringResource(place.details),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



@Composable
fun Logo(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "in launcher logo",
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
    )
}

@Composable
private fun ReplyHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Logo(
            modifier = Modifier
                .size(dimensionResource(R.dimen.topbar_logo_size))
                .padding(start = dimensionResource(R.dimen.topbar_logo_padding_start))
        )

    }
}
