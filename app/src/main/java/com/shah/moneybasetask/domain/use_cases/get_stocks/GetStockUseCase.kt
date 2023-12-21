package com.shah.moneybasetask.domain.use_cases.get_stocks

import com.shah.moneybasetask.common.Resource
import com.shah.moneybasetask.domain.model.Result
import com.shah.moneybasetask.domain.model.StockCustomModel
import com.shah.moneybasetask.domain.model.toExpectedResult
import com.shah.moneybasetask.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStockUseCase @Inject constructor(private val repository: StockRepository){
    operator fun invoke(region:String): Flow<Resource<List<StockCustomModel>>?> = flow {
        try {
            emit(Resource.LoadingProgress())
            val stocks = repository.getStocks(region)?.marketSummaryAndSparkResponse?.result?.map {
                it.toExpectedResult()
            }
            stocks?.let {
                emit(Resource.Success(stocks))
            }

        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}