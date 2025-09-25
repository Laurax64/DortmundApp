package com.example.dortmundapp.data

/**
 * Data class to represent a place
 */
data class Place(
    val id: Long,
    val name: Int,
    val details: Int,
    val painterResource: Int,
    var category: Category
)
