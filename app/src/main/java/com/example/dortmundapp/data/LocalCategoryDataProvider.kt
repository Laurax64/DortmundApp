package com.example.dortmundapp.data

import com.example.dortmundapp.R
import com.example.dortmundapp.model.Category

/**
 * Categories data
 */
object LocalCategoryDataProvider {
    val defaultCategory = getCategoryData()[0]
    val defaultPlace = defaultCategory.places[0]
    fun getCategoryData(): List<Category> {
        return listOf(
            Category(
                id = 1,
                titleResourceId = R.string.parks,
                imageResourceId = R.mipmap.ic_park,
                places = LocalPlacesDataProvider(1).getPlacesData()
            ),
            Category(
                id = 2,
                titleResourceId = R.string.forests,
                imageResourceId = R.mipmap.ic_forest,
                places = LocalPlacesDataProvider(2).getPlacesData()
            ),
            Category(
                id = 3,
                titleResourceId = R.string.swimming_pools,
                imageResourceId = R.mipmap.ic_pool,
                places = LocalPlacesDataProvider(3).getPlacesData()
            )

        )
    }
}