package com.shah.moneybasetask.domain.model.market_summary

import com.shah.moneybasetask.common.Utils
import java.util.UUID

data class Result(
    val cryptoTradeable: Boolean=false,
    val customPriceAlertConfidence: String="",
    val exchange: String="",
    val exchangeDataDelayedBy: Int=0,
    val exchangeTimezoneName: String="",
    val exchangeTimezoneShortName: String="",
    val firstTradeDateMilliseconds: Long=0,
    val fullExchangeName: String="",
    val gmtOffSetMilliseconds: Int=0,
    val language: String="",
    val market: String="",
    val marketState: String="",
    val priceHint: Int=0,
    val quoteType: String="",
    val region: String="",
    val regularMarketPreviousClose: RegularMarketPreviousClose,
    val regularMarketTime: RegularMarketTime,
    val shortName: String="",
    val sourceInterval: Int=0,
    val spark: Spark,
    val symbol: String="",
    val tradeable: Boolean=false,
    val triggerable: Boolean=false
)

fun Result.toExpectedResult(): StockCustomModel {
    return StockCustomModel(
        id = UUID.randomUUID().toString(),
        symbol = symbol,
        name = fullExchangeName,
        price = regularMarketPreviousClose.fmt,
        priceChange = Utils.getStocksResult(spark).second,
        upOrDown = Utils.getStocksResult(spark).first,
        spark)

}