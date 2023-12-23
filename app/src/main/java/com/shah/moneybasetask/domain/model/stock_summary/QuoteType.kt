package com.shah.moneybasetask.domain.model.stock_summary

data class QuoteType(
    val exchange: String = "",
    val exchangeTimezoneName: String = "",
    val exchangeTimezoneShortName: String = "",
    val gmtOffSetMilliseconds: String = "",
    val isEsgPopulated: Boolean = false,
    val longName: String = "",
    val market: String = "",
    val messageBoardId: String = "",
    val quoteType: String = "",
    val shortName: String = "",
    val symbol: String = ""
)