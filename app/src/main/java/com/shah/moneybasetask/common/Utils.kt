package com.shah.moneybasetask.common

import com.shah.moneybasetask.domain.model.market_summary.Spark
import kotlin.math.abs

object Utils {
    fun getStocksResult(sparkData: Spark): Pair<String,String>{
        // Sample code to calculate percentage change
        try {
            val previousClose = sparkData.previousClose
            val latestClose = sparkData.close.last()

            // Calculate percentage change
            val percentageChange = ((latestClose - previousClose) / previousClose) * 100

            // Determine if it's positive or negative
            val changeDirection = if (percentageChange > 0) "up" else "down"

            // Absolute value for displaying purposes
            val absolutePercentageChange = abs(percentageChange)

            val stockUpOrDown = if (changeDirection=="up") "+" else "-"

            return Pair(changeDirection,
                stockUpOrDown +String.format("%.2f",absolutePercentageChange)+"%")
        }catch (e:Exception){
            return Pair("not_moved","0.0%")
        }

    }


}