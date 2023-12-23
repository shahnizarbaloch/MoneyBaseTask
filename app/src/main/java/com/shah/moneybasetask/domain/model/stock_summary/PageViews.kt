package com.shah.moneybasetask.domain.model.stock_summary

data class PageViews(
    val longTermTrend: String = "",
    val maxAge: Number = 0,
    val midTermTrend: String = "",
    val shortTermTrend: String = ""
)