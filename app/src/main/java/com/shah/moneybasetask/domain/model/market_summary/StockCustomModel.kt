package com.shah.moneybasetask.domain.model.market_summary

import java.util.UUID

data class StockCustomModel(
    val id: String = UUID.randomUUID().toString(),
    val symbol:String="",
    val name:String="",
    val price:String="",
    val priceChange:String="",
    val upOrDown:String="",
    val spark: Spark=Spark()
){

}
