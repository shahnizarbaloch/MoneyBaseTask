package com.shah.moneybasetask.data.remote

import com.shah.moneybasetask.data.remote.dto.MarketSummaryResponse
import com.shah.moneybasetask.data.remote.dto.StockSummaryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {

    @GET("market/v2/get-summary")
    suspend fun getMarketSummary(@Query("region") region: String) : MarketSummaryResponse?

    @GET("stock/v2/get-summary")
    suspend fun getStockSummary(@Query("region") region: String,
                                @Query("symbol") symbol: String) : StockSummaryResponse
}