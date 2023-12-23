package com.shah.moneybasetask.presentation.detail_screen

import com.shah.moneybasetask.data.remote.dto.StockSummaryResponse
data class StockDetailsState (
    val isLoading: Boolean = false,
    val stockDetails: StockSummaryResponse = StockSummaryResponse(),
    val error: String = ""
)