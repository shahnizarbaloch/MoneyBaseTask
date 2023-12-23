package com.shah.moneybasetask.domain.repository

import com.shah.moneybasetask.domain.model.StockCustomModel

interface StockRepository {

    suspend fun getStocks(region: String): List<StockCustomModel>

}