package com.shah.moneybasetask.domain.model

data class MarketSummaryAndSparkResponse(
    val error: Any,
    val result: List<Stock>
)