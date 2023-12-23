package com.shah.moneybasetask.data.repository

import com.shah.moneybasetask.domain.model.StockCustomModel
import com.shah.moneybasetask.domain.repository.StockRepository

class FakeStockRepository : StockRepository{

    private val notes = mutableListOf<StockCustomModel>()
    override suspend fun getStocks(region: String): List<StockCustomModel> {
        return notes
    }


}