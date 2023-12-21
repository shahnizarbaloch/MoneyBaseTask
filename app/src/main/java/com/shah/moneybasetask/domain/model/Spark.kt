package com.shah.moneybasetask.domain.model

data class Spark(
    val chartPreviousClose: Double=0.0,
    val close: List<Double>,
    val dataGranularity: Int=0,
    val end: Int=0,
    val previousClose: Double=0.0,
    val start: Int=0,
    val symbol: String="",
    val timestamp: List<Int>
)