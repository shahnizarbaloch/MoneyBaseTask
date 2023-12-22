package com.shah.moneybasetask.presentation.detail_screen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shah.moneybasetask.databinding.StockDetailsScreenBinding
import com.shah.moneybasetask.domain.model.StockCustomModelParcelable

class StockDetailsActivity: AppCompatActivity() {

    private lateinit var binding: StockDetailsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StockDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeOtherComponents()

    }

    @SuppressLint("SetTextI18n")
    private fun initializeOtherComponents() {
        val stock: StockCustomModelParcelable? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_STOCK",StockCustomModelParcelable::class.java)
        } else {
            intent.getParcelableExtra("EXTRA_STOCK")
        }

        stock?.let {
            binding.tvStockName.text = "${stock.symbol} (${stock.name})"
            binding.tvStockPrice.text = "$${stock.price}"
            binding.tvStockPercentage.text = if (stock.upOrDown == "up"){
                "+${stock.priceChange}"
            }else{
                "-${stock.priceChange}"
            }
        }
        binding.imgBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }


}