package com.shah.moneybasetask.di

import android.content.Context
import com.shah.moneybasetask.MoneyBaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MoneyBaseApplication {
        return app as MoneyBaseApplication
    }



}