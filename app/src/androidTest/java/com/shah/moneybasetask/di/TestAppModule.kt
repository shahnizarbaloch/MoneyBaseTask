package com.shah.moneybasetask.di

import com.shah.moneybasetask.data.remote.ApiCalls
import com.shah.moneybasetask.data.repository.MarketSummaryRepositoryImpl
import com.shah.moneybasetask.domain.repository.MarketSummaryRepository
import com.shah.moneybasetask.domain.use_cases.feature_market_summary.GetMarketSummaryUseCase
import com.shah.moneybasetask.presentation.main_screen.StockListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideStocksApi(): ApiCalls {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("", "")
                    .addHeader("", "")
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiCalls::class.java)
    }

    @Provides
    @Singleton
    fun provideMarketSummaryRepository(api: ApiCalls): MarketSummaryRepository {
        return MarketSummaryRepositoryImpl(api)
    }

    /*@Provides
    @Singleton
    fun providesUseCaseMarketSummary(api: ApiCalls) : GetMarketSummaryUseCase{
        return GetMarketSummaryUseCase(MarketSummaryRepositoryImpl(api))
    }*/


}