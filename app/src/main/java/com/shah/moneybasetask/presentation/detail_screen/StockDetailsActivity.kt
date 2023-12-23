package com.shah.moneybasetask.presentation.detail_screen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.shah.moneybasetask.R
import com.shah.moneybasetask.databinding.StockDetailsScreenBinding
import com.shah.moneybasetask.domain.model.market_summary.StockCustomModelParcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockDetailsActivity : AppCompatActivity() {

    private lateinit var binding: StockDetailsScreenBinding

    private val viewModel: DetailScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StockDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeOtherComponents()

    }

    @SuppressLint("SetTextI18n")
    private fun initializeOtherComponents() {
        val selectedStock: StockCustomModelParcelable? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("EXTRA_STOCK", StockCustomModelParcelable::class.java)
            } else {
                intent.getParcelableExtra("EXTRA_STOCK")
            }

        //calling api with required parameter
        viewModel.getStockSummary("US", selectedStock?.symbol ?: "BTC-USD")

        binding.includedToolbar.tvToolbarText.text =
            "${selectedStock?.symbol} (${selectedStock?.name})"

        observeViewModel(selectedStock)

        binding.includedToolbar.imgGoBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel(selectedStock: StockCustomModelParcelable?) {
        viewModel.state.observe(this) { state ->
            when {
                state.isLoading -> {
                    // Show progress bar
                    binding.clStockDetails.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvErrorMessage.visibility = View.GONE
                    binding.clStockDetails.visibility = View.GONE
                }

                state.error.isNotBlank() -> {
                    // Show error message
                    binding.progressBar.visibility = View.GONE
                    binding.tvErrorMessage.visibility = View.VISIBLE
                    binding.clStockDetails.visibility = View.GONE
                    binding.tvErrorMessage.text = state.error
                }

                else -> {
                    // Data loaded successfully
                    binding.clStockDetails.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.tvErrorMessage.visibility = View.GONE
                    binding.clStockDetails.visibility = View.VISIBLE

                    val stockDetails = state.stockDetails
                    binding.tvPrice.text = "$${selectedStock?.price}"

                    val stockMove : Pair<Int,Int> = when (selectedStock?.upOrDown) {
                        "up" -> Pair(Color.GREEN,R.drawable.ic_stock_up)
                        "down" -> Pair(Color.RED,R.drawable.ic_stock_down)
                        else -> Pair(Color.DKGRAY,R.drawable.ic_stock_not_moved)
                    }

                    Glide.with(this).load(stockMove.second).into(binding.imgUpOrDownIcon)
                    binding.tvPercentage.text = selectedStock?.priceChange
                    binding.tvPercentage.setTextColor(stockMove.first)

                    binding.tvMarketOpenRate.text = stockDetails.summaryDetail.open.fmt
                    binding.tvMarketPreviousClose.text =
                        stockDetails.summaryDetail.previousClose.fmt

                    binding.tvTotalMarketCap.text = stockDetails.summaryDetail.marketCap.fmt
                    binding.tvAverageDailyVolume.text = stockDetails.summaryDetail.averageVolume.fmt

                    binding.tv24HoursLow.text = stockDetails.summaryDetail.dayLow.fmt
                    binding.tv24HoursHigh.text = stockDetails.summaryDetail.dayHigh.fmt

                    binding.tvCirculatingSupply.text =
                        stockDetails.summaryDetail.circulatingSupply.fmt
                    binding.tv10DayAverage.text = stockDetails.summaryDetail.averageVolume10days.fmt
                    binding.tv50DayAverage.text = stockDetails.summaryDetail.fiftyDayAverage.fmt

                    if (stockDetails.summaryProfile.description.isNotBlank()) {
                        binding.tvDescription.text =
                            "Description: ${stockDetails.summaryProfile.description}"
                    }

                }
            }
        }
    }


}