package com.shah.moneybasetask.presentation.main_screen

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.shah.moneybasetask.common.TestConstants
import com.shah.moneybasetask.di.AppModule
import com.shah.moneybasetask.domain.model.Spark
import com.shah.moneybasetask.domain.model.StockCustomModel
import com.shah.moneybasetask.presentation.MainActivity
import com.shah.moneybasetask.presentation.ui.theme.MoneyBaseTaskTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.coroutines.coroutineContext

@HiltAndroidTest
@UninstallModules(AppModule::class)
class StockListScreenTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)
    
    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()
    
    @Before
    fun setup(){
        hiltRule.inject()
        composeRule.activity.setContent {
            MoneyBaseTaskTheme {
                StockListScreen()
            }
        }
    }

    @Test
    fun testLazyColumnIsEmpty(){
        composeRule.onNodeWithTag(TestConstants.STOCK_LAZY_COLUMN)
            .onChildren()
            .assertCountEquals(0)
    }

    @Test
    fun writeSomethingInTheSearchViewToTestIt() = runBlocking{

            val inputText = "This is just for testing"
            for (char in inputText) {
                composeRule.onNodeWithTag(TestConstants.SEARCH_BAR)
                    .performTextInput(char.toString())
                composeRule.awaitIdle()
                // Just for testing purpose
                delay(200)
            }


    }


}