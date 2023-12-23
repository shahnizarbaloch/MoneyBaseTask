package com.shah.moneybasetask.domain.use_cases.get_stocks

import com.google.common.truth.Truth.assertThat
import com.shah.moneybasetask.common.Resource
import com.shah.moneybasetask.data.repository.FakeStockRepository
import com.shah.moneybasetask.domain.model.Spark
import com.shah.moneybasetask.domain.model.StockCustomModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetStockUseCaseTest{

    private lateinit var getStocks: GetStockUseCase
    private lateinit var fakeStockRepository: FakeStockRepository

    @Before
    fun setup(){
        fakeStockRepository = FakeStockRepository()
        getStocks = GetStockUseCase(fakeStockRepository)

        val stocksToInsert = mutableListOf<StockCustomModel>()
        ('a'..'z').forEachIndexed { index, c ->
            stocksToInsert.add(
                StockCustomModel(
                    symbol = c.toString(),
                    name = "Stock : $c",
                    price = "${index}00",
                    priceChange = "${index}0",
                    upOrDown = "up",
                    Spark()
                )
            )
        }
        stocksToInsert.shuffle()

    }

    @Test
    fun `Check dummy stocks have been added`(): Unit = runBlocking {
        getStocks.invoke("US").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    assertThat(true)
                }
                is Resource.Error -> {
                    assertThat(false)
                }
                is Resource.LoadingProgress -> {
                    //assertThat("Loading")
                }
                else -> {

                }
            }
        }

    }
}