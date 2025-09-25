package com.example.dortmundapp.utils

/**
 * Different types of navigation supported by app depending on size and state.
 */
enum class NavigationType {
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}

/**
 * Content shown depending on size and state of device.
 */
enum class ContentType {
    RECOMMENDATIONS_ONLY, RECOMMENDATIONS_AND_DETAIL
}
