package com.example.dortmundapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val id: Int,
    val places: List<Place>,
    @StringRes val titleResourceId: Int,
    @DrawableRes val imageResourceId: Int
    )
