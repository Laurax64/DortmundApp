package com.example.dortmundapp.data

import com.example.dortmundapp.R
import com.example.dortmundapp.model.Place

/**
 * Places data
 */
class LocalPlacesDataProvider(private val categoryID: Int) {

    fun getPlacesData(): List<Place> {
        return when (categoryID) {
            1 -> parkList()
            2 -> forestList()
            else -> poolList()
        }
    }

    private fun parkList(): List<Place> {
        return listOf(
            Place(
                id = 1,
                titleResourceId = R.string.westfalenpark_dortmund,
                placeDetails = R.string.place_details
            ),
            Place(
                id = 2,
                titleResourceId = R.string.Westpark,
                placeDetails = R.string.place_details
            ),
            Place(
                id = 3,
                titleResourceId = R.string.Fredenbaumpark,
                placeDetails = R.string.place_details
            )
        )
    }

    private fun forestList(): List<Place> {
        return listOf(
            Place(
                id = 4,
                titleResourceId = R.string.burgholzwald,
                placeDetails = R.string.place_details
            ),
            Place(
                id = 5,
                titleResourceId = R.string.grävingholz,
                placeDetails = R.string.place_details
            ),
            Place(
                id = 6,
                titleResourceId = R.string.aplerbecker_wald,
                placeDetails = R.string.place_details
            )
        )
    }

    private fun poolList(): List<Place> {
        return listOf(
            Place(
                id = 7,
                titleResourceId = R.string.burgholzwald,
                placeDetails = R.string.place_details
            ),
            Place(
                id = 8,
                titleResourceId = R.string.grävingholz,
                placeDetails = R.string.place_details
            ),
            Place(
                id = 9,
                titleResourceId = R.string.aplerbecker_wald,
                placeDetails = R.string.place_details
            )
        )
    }
}