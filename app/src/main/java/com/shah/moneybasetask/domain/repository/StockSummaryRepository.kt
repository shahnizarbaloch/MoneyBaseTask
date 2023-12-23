package com.shah.moneybasetask.domain.repository

import com.shah.moneybasetask.data.remote.dto.StockSummaryResponse

interface StockSummaryRepository {

    suspend fun getStockSummary(region: String,symbol:String): StockSummaryResponse

}