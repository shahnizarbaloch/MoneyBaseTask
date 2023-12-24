package com.shah.moneybasetask.presentation.main_screen


import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shah.moneybasetask.R
import com.shah.moneybasetask.common.TestConstants
import com.shah.moneybasetask.domain.model.market_summary.StockCustomModelParcelable
import com.shah.moneybasetask.presentation.detail_screen.StockDetailsActivity
import com.shah.moneybasetask.presentation.main_screen.components.StockListItem

@Composable
fun StockListScreen(viewModel: StockListViewModel = hiltViewModel()) {


    // Access the Context using LocalContext
    val context: Context = LocalContext.current

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        Column {


            TextField(
                value = viewModel.search,
                onValueChange = {
                    viewModel.updateSearchText(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .testTag(TestConstants.SEARCH_BAR),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = ""
                    )
                },
                singleLine = true,
                placeholder = {
                    Text(text = "Search")
                },
                )
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.CenterHorizontally)
                        .testTag(TestConstants.ERROR_MESSAGE)

                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                        .testTag(TestConstants.PROGRESS_BAR)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .testTag(TestConstants.STOCK_LAZY_COLUMN)
            ) {
                items(viewModel.filteredStocks.value) { stock ->
                    StockListItem(stock = stock,
                        onItemClick = {
                            //Open stock details activity here
                            val obj = StockCustomModelParcelable(stock.symbol,stock.name,stock.price,stock.priceChange,stock.upOrDown)

                            val intent = Intent(context, StockDetailsActivity::class.java).apply {
                                putExtra("EXTRA_STOCK", obj)
                            }
                            context.startActivity(intent)
                        })
                }
            }


        }


    }

}
