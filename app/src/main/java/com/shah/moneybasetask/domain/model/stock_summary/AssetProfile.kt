package com.shah.moneybasetask.domain.model.stock_summary

data class AssetProfile(
    val companyOfficers: List<Any> = listOf(),
    val description: String = "",
    val maxAge: Number = 0,
    val name: String = "",
    val startDate: String = ""
)