package com.shah.moneybasetask.domain.use_cases.feature_market_summary

import com.shah.moneybasetask.common.Resource
import com.shah.moneybasetask.domain.model.market_summary.StockCustomModel
import com.shah.moneybasetask.domain.repository.MarketSummaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMarketSummaryUseCase @Inject constructor(private val repository: MarketSummaryRepository){
    operator fun invoke(region:String): Flow<Resource<List<StockCustomModel>>?> = flow {
        try {
            emit(Resource.LoadingProgress())
            val stocks = repository.getMarketSummary(region).map {
                it
            }
            emit(Resource.Success(stocks))

        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}