package com.shah.moneybasetask.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shah.moneybasetask.common.Resource
import com.shah.moneybasetask.domain.model.market_summary.StockCustomModel
import com.shah.moneybasetask.domain.use_cases.feature_market_summary.GetMarketSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(private val useCase: GetMarketSummaryUseCase) : ViewModel(){

    private val _state = mutableStateOf(StockListState())
    val state:State<StockListState> = _state

    private val _filteredStocks = MutableStateFlow<List<StockCustomModel>>(emptyList())
    val filteredStocks: StateFlow<List<StockCustomModel>> get() = _filteredStocks

    var search by mutableStateOf("")
        private set
    fun updateSearchText(input:String){
        search = input
        filterStocks()
    }


    init {
        viewModelScope.launch {
           // while (true) {
                getStocks()
                //delay(8000)
           // }
        }

    }

    private fun getStocks() {
        useCase("US").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = StockListState(stocks = result.data ?: emptyList())
                    _filteredStocks.value = _state.value.stocks
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

    private fun filterStocks() {
        val filteredList = if (search.isBlank()) {
            state.value.stocks // Return all stocks if search query is empty
        } else {
            state.value.stocks.filter { stock ->
                stock.symbol.contains(search, ignoreCase = true) ||
                        stock.name.contains(search, ignoreCase = true)
            }
        }
        _filteredStocks.value = filteredList
    }
}