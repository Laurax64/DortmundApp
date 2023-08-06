package com.example.dortmundapp.model

import androidx.annotation.StringRes

data class Place(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val placeDetails: Int
)
