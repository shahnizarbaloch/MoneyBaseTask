package com.shah.moneybasetask.data.remote

import com.shah.moneybasetask.data.remote.dto.MarketSummaryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    /*@Headers(
        "X-RapidAPI-Key: e17c2a5d4bmshc17564888f42bcdp1cd6f0jsnedb47f780fcb",
        "X-RapidAPI-Host: yh-finance.p.rapidapi.com"
    )*/
    @GET("market/v2/get-summary")
    suspend fun getStocks(@Query("region") region: String) : MarketSummaryResponse?
}