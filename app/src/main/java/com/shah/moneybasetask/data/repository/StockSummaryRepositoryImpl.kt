package com.shah.moneybasetask.data.repository

import com.shah.moneybasetask.data.remote.ApiCalls
import com.shah.moneybasetask.data.remote.dto.StockSummaryResponse
import com.shah.moneybasetask.domain.repository.StockSummaryRepository
import javax.inject.Inject

class StockSummaryRepositoryImpl @Inject constructor(private val api: ApiCalls) : StockSummaryRepository {
    override suspend fun getStockSummary(region: String, symbol: String): StockSummaryResponse {
        return api.getStockSummary(region,symbol)
    }
}