package com.swensonhe.common.entities

data class Location(
    val id: Long,
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val url: String,
)