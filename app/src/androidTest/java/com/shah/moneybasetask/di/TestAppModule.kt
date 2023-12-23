package com.shah.moneybasetask.di

import android.content.Context
import com.shah.moneybasetask.MoneyBaseApplication
import com.shah.moneybasetask.data.remote.StockApi
import com.shah.moneybasetask.data.repository.StockRepositoryImpl
import com.shah.moneybasetask.domain.repository.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MoneyBaseApplication {
        return app as MoneyBaseApplication
    }

    @Provides
    @Singleton
    fun provideStocksApi():StockApi {

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
            .create(StockApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStockRepository(api: StockApi):StockRepository {
        return StockRepositoryImpl(api)
    }
}