package com.shah.moneybasetask.domain.model.market_summary

data class StockCustomModel(
    val symbol:String,
    val name:String,
    val price:String,
    val priceChange:String,
    val upOrDown:String,
    val spark: Spark
){

}
