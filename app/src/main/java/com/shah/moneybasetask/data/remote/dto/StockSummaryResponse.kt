package com.shah.moneybasetask.data.remote.dto

import com.shah.moneybasetask.domain.model.stock_summary.AssetProfile
import com.shah.moneybasetask.domain.model.stock_summary.PageViews
import com.shah.moneybasetask.domain.model.stock_summary.QuoteType
import com.shah.moneybasetask.domain.model.stock_summary.SummaryDetail
import com.shah.moneybasetask.domain.model.stock_summary.SummaryProfile

data class StockSummaryResponse(
    val assetProfile: AssetProfile = AssetProfile(),
    val pageViews: PageViews = PageViews(),
    val quoteType: QuoteType = QuoteType(),
    val summaryDetail: SummaryDetail = SummaryDetail(),
    val summaryProfile: SummaryProfile = SummaryProfile(),
    val symbol: String = ""
)