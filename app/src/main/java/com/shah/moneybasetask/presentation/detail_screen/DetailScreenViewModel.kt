package com.shah.moneybasetask.presentation.detail_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shah.moneybasetask.common.Resource
import com.shah.moneybasetask.data.remote.dto.StockSummaryResponse
import com.shah.moneybasetask.domain.use_cases.feature_stock_summary.GetStockSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val useCase: GetStockSummaryUseCase) : ViewModel() {

    private val _state = MutableLiveData<StockDetailsState>()
    val state: LiveData<StockDetailsState> = _state

    fun getStockSummary(region: String, symbol: String) {

        useCase.invoke(region, symbol).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = StockDetailsState(stockDetails = result.data ?: StockSummaryResponse())
                }
                is Resource.Error -> {
                    _state.value = StockDetailsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.LoadingProgress -> {
                    _state.value = StockDetailsState(isLoading = true)
                }
                else -> {
                    _state.value = StockDetailsState(
                        error = result?.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}