package com.shah.moneybasetask.presentation.main_screen

import com.shah.moneybasetask.domain.model.market_summary.StockCustomModel

data class StockListState (
    val isLoading: Boolean = false,
    val stocks: List<StockCustomModel> = emptyList(),
    val error: String = ""
)