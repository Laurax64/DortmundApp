package com.example.dortmundapp.data

import com.example.dortmundapp.R
import com.example.dortmundapp.data.Category.*

/**
 * Places data
 */
object LocalPlacesDataProvider {
    val allPlaces = listOf(
        Place(
            id = 1,
            name = R.string.westfalenpark_dortmund,
            details = R.string.place_details,
            painterResource = R.drawable.park,
            category = Parks
        ),
        Place(
            id = 2,
            name = R.string.Westpark,
            details = R.string.place_details,
            painterResource = R.drawable.park,
            category = Parks
        ),
        Place(
            id = 3,
            name = R.string.Fredenbaumpark,
            details = R.string.place_details,
            painterResource = R.drawable.park,
            category = Parks
        ),
        Place(
            id = 4,
            name = R.string.burgholzwald,
            details = R.string.place_details,
            painterResource = R.drawable.forest,
            category = Forests
        ),
        Place(
            id = 5,
            name = R.string.grävingholz,
            details = R.string.place_details,
            painterResource = R.drawable.forest,
            category = Forests
        ),
        Place(
            id = 6,
            name = R.string.aplerbecker_wald,
            details = R.string.place_details,
            painterResource = R.drawable.forest,
            category = Forests
        ),
        Place(
            id = 7,
            name = R.string.Freibad_Hengstey,
            details = R.string.place_details,
            painterResource = R.drawable.pool,
            category = Pools
        ),
        Place(
            id = 8,
            name = R.string.Freibad_Stockheide,
            details = R.string.place_details,
            painterResource = R.drawable.pool,
            category = Pools
        ),
        Place(
            id = 9,
            name = R.string.Solebad_Wischlingen,
            details = R.string.place_details,
            painterResource = R.drawable.pool,
            category = Pools
        ),
        )

    /**
     * Get a [Place] with the given [id].
     */
    fun get(id: Long): Place? {
        return allPlaces.firstOrNull { it.id == id }
    }

    val defaultPlace = allPlaces[7]

}














