package com.shah.moneybasetask.data.repository

import com.shah.moneybasetask.data.remote.ApiCalls
import com.shah.moneybasetask.domain.model.market_summary.StockCustomModel
import com.shah.moneybasetask.domain.model.market_summary.toExpectedResult
import com.shah.moneybasetask.domain.repository.MarketSummaryRepository
import javax.inject.Inject

class MarketSummaryRepositoryImpl @Inject constructor(private val api:ApiCalls) : MarketSummaryRepository {
    override suspend fun getMarketSummary(region: String): List<StockCustomModel> {

        val listOfStock = mutableListOf<StockCustomModel>()

        val apiResponse = api.getMarketSummary(region)
        apiResponse?.let {
            val result = apiResponse.marketSummaryAndSparkResponse.result
            for (i in result.indices){
                listOfStock.add(result[i].toExpectedResult())
            }
        }

        //Log.d("Response","Result: ${api.getStocks(region)?.marketSummaryAndSparkResponse?.result}")
        return listOfStock
    }
}