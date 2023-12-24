package com.shah.moneybasetask.presentation.main_screen

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.ViewModelProvider
import com.shah.moneybasetask.common.TestConstants
import com.shah.moneybasetask.di.TestAppModule
import com.shah.moneybasetask.presentation.MainActivity
import com.shah.moneybasetask.presentation.ui.theme.MoneyBaseTaskTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(TestAppModule::class)
class StockListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: StockListViewModel


    @Before
    fun setup() {
        hiltRule.inject()

        composeRule.activityRule.scenario.onActivity {
            viewModel = ViewModelProvider(it)[StockListViewModel::class.java]
            viewModel.setLazyColumnValuesForTesting()
        }

        composeRule.activity.setContent {
            MoneyBaseTaskTheme {
                StockListScreen()

            }
        }
    }

    @Test
    fun testLoadingStateShowsProgressBar() {
        // Ensure that the initial state is loading
        viewModel.showLoadingBarForTestingOnly()
        val state = viewModel.state.value
        Assert.assertTrue(state.isLoading)

        // Assert that the progress bar is displayed
        composeRule.onNodeWithTag(TestConstants.PROGRESS_BAR)
            .assertIsDisplayed()
    }

    @Test
    fun testSuccessStateShowsDataList() = runBlocking {
        // Set the view model state to a success state with a list of data

        if (viewModel.filteredStocks.value.isNotEmpty()) {
            // Assert that the LazyColumn or any other component displaying the list is displayed
            composeRule.onNodeWithTag(TestConstants.STOCK_LAZY_COLUMN)
                .assertIsDisplayed()
                .apply {
                    val inputText = "BTC-USD"
                    for (char in inputText) {
                        composeRule.onNodeWithTag(TestConstants.SEARCH_BAR)
                            .performTextInput(char.toString())
                        composeRule.awaitIdle()
                        // Just for testing purpose
                        delay(200)
                        // Just for testing purpose
                    }

                    /*composeRule.onNodeWithTag(TestConstants.STOCK_LAZY_COLUMN)
                        .onChildren()
                        .assertCountEquals(1)*/
                }
        }


    }

    /*@Test
    fun testLazyColumnIsEmpty(){
        composeRule.onNodeWithTag(TestConstants.STOCK_LAZY_COLUMN)
            .onChildren()
            .assertCountEquals(0)
    }*/


}