package com.shah.moneybasetask.data.repository

import com.shah.moneybasetask.domain.model.market_summary.StockCustomModel
import com.shah.moneybasetask.domain.repository.MarketSummaryRepository

class FakeMarketSummaryRepository : MarketSummaryRepository{

    private val stockList = mutableListOf<StockCustomModel>()
    override suspend fun getMarketSummary(region: String): List<StockCustomModel> {
        return stockList
    }


}