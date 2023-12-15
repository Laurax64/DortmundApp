package com.example.dortmundapp.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.dortmundapp.R
import com.example.dortmundapp.data.Place

/**
 * Displays a Place with its details
 */
@Composable
fun PlaceDetailsScreen(
    uiState: UiState,
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
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .padding(top = dimensionResource(R.dimen.detail_card_list_padding_top))
        ) {
            item {
                if (isFullScreen) {
                    ReplyDetailsScreenTopBar(
                        onBackPressed,
                        uiState,
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = dimensionResource(R.dimen.detail_topbar_padding_bottom))
                    )
                }
                PlaceDetailsCard(
                    place = uiState.currentSelectedPlace,
                    isFullScreen = isFullScreen,
                    modifier = if (isFullScreen)
                        Modifier.padding(horizontal = dimensionResource(R.dimen.detail_card_outer_padding_horizontal))
                    else
                        Modifier.padding(end = dimensionResource(R.dimen.detail_card_outer_padding_horizontal))
                )
            }
        }
    }
}

@Composable
private fun ReplyDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    uiState: UiState,
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
                imageVector = Icons.Default.ArrowBack,
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
private fun PlaceDetailsCard(
    place: Place,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false,
) {
    val context = LocalContext.current
    val displayToast = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.detail_card_inner_padding))
        ) {
            DetailsScreenHeader(
                place,
                Modifier.fillMaxWidth()
            )
            if (!isFullScreen) {
                Text(
                    text = stringResource(place.name),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.detail_content_padding_top),
                        bottom = dimensionResource(R.dimen.detail_expanded_subject_body_spacing)
                    ),
                )
            } else {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.detail_content_padding_top)))
            }
            Column {
                Image(painter = painterResource(place.painterResource),
                    contentDescription = stringResource(place.name),
                    modifier = modifier,
                )
                Text(
                    text = stringResource(place.details),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }


        }
    }
}


@Composable
private fun DetailsScreenHeader(place: Place, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.email_header_content_padding_horizontal),
                    vertical = dimensionResource(R.dimen.email_header_content_padding_vertical)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(place.name),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

