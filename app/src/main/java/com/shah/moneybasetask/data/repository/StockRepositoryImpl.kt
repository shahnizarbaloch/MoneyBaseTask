package com.shah.moneybasetask.data.repository

import com.shah.moneybasetask.data.remote.StockApi
import com.shah.moneybasetask.domain.model.StockCustomModel
import com.shah.moneybasetask.domain.model.toExpectedResult
import com.shah.moneybasetask.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(private val api:StockApi) : StockRepository {
    override suspend fun getStocks(region: String): List<StockCustomModel> {

        val listOfStock = mutableListOf<StockCustomModel>()

        val apiResponse = api.getStocks(region)
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