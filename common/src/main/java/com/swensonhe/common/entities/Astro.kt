package com.swensonhe.common.entities

data class Astro(
    val sunrise:String,
    val sunset:String,
    val moonrise:String,
    val moonset:String,
    val moonPhase:String,
    val moonIllumination:String,
    val isMoonUp: Int,
    val isSunUp: Int,
)
