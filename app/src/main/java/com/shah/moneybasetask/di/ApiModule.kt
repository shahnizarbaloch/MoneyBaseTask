package com.shah.moneybasetask.di

import com.shah.moneybasetask.data.remote.ApiCalls
import com.shah.moneybasetask.data.repository.MarketSummaryRepositoryImpl
import com.shah.moneybasetask.data.repository.StockSummaryRepositoryImpl
import com.shah.moneybasetask.domain.repository.MarketSummaryRepository
import com.shah.moneybasetask.domain.repository.StockSummaryRepository
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
object ApiModule {
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
                    .addHeader("X-RapidAPI-Key", "e17c2a5d4bmshc17564888f42bcdp1cd6f0jsnedb47f780fcb")
                    .addHeader("X-RapidAPI-Host", "yh-finance.p.rapidapi.com")
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://yh-finance.p.rapidapi.com/")
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

    @Provides
    @Singleton
    fun provideStockSummaryRepository(api: ApiCalls): StockSummaryRepository {
        return StockSummaryRepositoryImpl(api)
    }
}