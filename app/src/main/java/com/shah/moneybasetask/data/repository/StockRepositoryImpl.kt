package com.shah.moneybasetask.data.repository

import com.shah.moneybasetask.data.remote.StockApi
import com.shah.moneybasetask.data.remote.dto.MarketSummaryResponse
import com.shah.moneybasetask.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(private val api:StockApi) : StockRepository {
    override suspend fun getStocks(region: String): MarketSummaryResponse {
        //Log.d("Response","Result: ${api.getStocks(region)?.marketSummaryAndSparkResponse?.result}")
        return api.getStocks(region)!!
    }
}