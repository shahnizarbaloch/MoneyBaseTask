package com.shah.moneybasetask.domain.use_cases.feature_stock_summary

import com.shah.moneybasetask.common.Resource
import com.shah.moneybasetask.data.remote.dto.StockSummaryResponse
import com.shah.moneybasetask.domain.repository.StockSummaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStockSummaryUseCase @Inject constructor(private val repository: StockSummaryRepository){
    operator fun invoke(region:String,symbol:String): Flow<Resource<StockSummaryResponse>?> = flow {
        try {
            emit(Resource.LoadingProgress())
            val stockDetails = repository.getStockSummary(region,symbol)
            emit(Resource.Success(stockDetails))

        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}