package com.shah.moneybasetask.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shah.moneybasetask.common.Resource
import com.shah.moneybasetask.domain.use_cases.get_stocks.GetStockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(private val getStockUseCase: GetStockUseCase) : ViewModel(){

    private val _state = mutableStateOf(StockListState())
    val state:State<StockListState> = _state

    init {
        getStocks()
    }

    private fun getStocks() {
        getStockUseCase("US").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = StockListState(stocks = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = StockListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.LoadingProgress -> {
                    _state.value = StockListState(isLoading = true)
                }
                else -> {
                    _state.value = StockListState(
                        error = result?.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}