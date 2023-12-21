package com.shah.moneybasetask.domain.repository

import com.shah.moneybasetask.data.remote.dto.MarketSummaryResponse

interface StockRepository {

    suspend fun getStocks(region: String): MarketSummaryResponse?

}