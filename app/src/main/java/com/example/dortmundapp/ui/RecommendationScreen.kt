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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.dortmundapp.R
import com.example.dortmundapp.data.Category
import com.example.dortmundapp.data.Place
import com.example.dortmundapp.utils.ContentType
import com.example.dortmundapp.utils.NavigationType

/**
 * Displays the navigation drawer
 */
@Composable
fun RecommendationScreen(
    navigationType: NavigationType,
    contentType: ContentType,
    uiState: UiState,
    onTabPressed: (Category) -> Unit,
    onPlaceCardPressed: (Place) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            category = Category.Pools,
            icon = painterResource(R.drawable.pool),
            text = stringResource(id = R.string.swimming_pools)
        ),
        NavigationItemContent(
            category = Category.Forests,
            icon = painterResource(R.drawable.forest),
            text = stringResource(id = R.string.forests)
        ),
        NavigationItemContent(
            category = Category.Parks,
            icon = painterResource(R.drawable.park),
            text = stringResource(id = R.string.parks)
        )
    )

    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        val navigationDrawerContentDescription = stringResource(R.string.navigation_drawer)
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                    NavigationDrawerContent(
                        selectedDestination = uiState.currentCategory,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(dimensionResource(R.dimen.drawer_padding_content))
                    )
                }
            },
            modifier = modifier.testTag(navigationDrawerContentDescription)
        ) {
            AppContent(
                navigationType = navigationType,
                contentType = contentType,
                uiState = uiState,
                onTabPressed = onTabPressed,
                onPlaceCardPressed = onPlaceCardPressed,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier
            )
        }
    } else {
        if (uiState.isShowingRecommendations) {
            AppContent(
                navigationType = navigationType,
                contentType = contentType,
                uiState = uiState,
                onTabPressed = onTabPressed,
                onPlaceCardPressed = onPlaceCardPressed,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier
            )
        } else {
            PlaceDetailsScreen(
                uiState = uiState,
                isFullScreen = true,
                modifier = modifier,
                onBackPressed = onDetailScreenBackPressed
            )
        }
    }
}


@Composable
private fun AppContent(
    navigationType: NavigationType,
    contentType: ContentType,
    uiState: UiState,
    onTabPressed: ((Category) -> Unit),
    onPlaceCardPressed: (Place) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                NavigationRail(
                    currentTab = uiState.currentCategory,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier
                        .testTag(navigationRailContentDescription)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                if (contentType == ContentType.RECOMMENDATIONS_AND_DETAIL) {
                    ListAndDetailContent(
                        uiState = uiState,
                        onPlaceCardPressed = onPlaceCardPressed,
                        modifier = Modifier.weight(1f),
                    )
                } else {
                    ListOnlyContent(
                        uiState = uiState,
                        onPlaceCardPressed = onPlaceCardPressed,
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                horizontal = dimensionResource(R.dimen.email_list_only_horizontal_padding)
                            )
                    )
                }
                AnimatedVisibility(
                    visible = navigationType == NavigationType.BOTTOM_NAVIGATION
                ) {
                    val bottomNavigationContentDescription =
                        stringResource(R.string.navigation_bottom)
                    BottomNavigationBar(
                        currentCategory = uiState.currentCategory,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(bottomNavigationContentDescription)
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationRail(
    currentTab: Category,
    onTabPressed: ((Category) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == navItem.category,
                onClick = { onTabPressed(navItem.category) },
                icon = {
                    Icon(painter = navItem.icon, contentDescription = navItem.text )
                }
            )
        }
    }
}

@Composable
private fun BottomNavigationBar(
    currentCategory: Category,
    onTabPressed: ((Category) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentCategory == navItem.category,
                onClick = { onTabPressed(navItem.category) },
                icon = {
                    Icon(painter = navItem.icon, contentDescription = navItem.text )
                }
            )
        }
    }
}

@Composable
private fun NavigationDrawerContent(
    selectedDestination: Category,
    onTabPressed: ((Category) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        NavigationDrawerHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.profile_image_padding)),
        )
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = selectedDestination == navItem.category,
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.drawer_padding_header))
                    )
                },
                icon = {
                    Icon(painter = navItem.icon, contentDescription = navItem.text )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = { onTabPressed(navItem.category) }
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium)
    }
}

/**
 * Data class for the category navigation items
 */
private data class NavigationItemContent(
    val category: Category,
    val icon: Painter,
    val text: String
)