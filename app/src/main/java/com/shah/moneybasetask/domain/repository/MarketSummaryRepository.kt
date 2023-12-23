package com.shah.moneybasetask.domain.repository

import com.shah.moneybasetask.domain.model.market_summary.StockCustomModel

interface MarketSummaryRepository {

    suspend fun getMarketSummary(region: String): List<StockCustomModel>

}