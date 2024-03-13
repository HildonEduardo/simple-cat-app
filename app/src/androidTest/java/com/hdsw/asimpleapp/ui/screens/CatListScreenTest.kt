package com.hdsw.asimpleapp.ui.screens

import android.content.res.Configuration
import androidx.activity.compose.setContent
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hdsw.asimpleapp.ASimpleAppNavHost
import com.hdsw.asimpleapp.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by hildon.lima<hildon.eduardo@gmail.com> on 13/03/24.
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CatListScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testOrientationPortrait() {
        composeTestRule.activity.setContent {
            ASimpleAppNavHost(orientation = Configuration.ORIENTATION_PORTRAIT)
        }

        // Verify the initial state of the app
        composeTestRule.onNodeWithTag("cats_list").assertExists()
    }

    @Test
    fun testOrientationLandscape() {
        composeTestRule.activity.setContent {
            ASimpleAppNavHost(orientation = Configuration.ORIENTATION_LANDSCAPE)
        }

        // Verify the initial state of the app
        composeTestRule.onNodeWithTag("cats_grid").assertExists()
    }

    @Test
    fun testScrollToValidImageItem() {
        composeTestRule.activity.setContent {
            ASimpleAppNavHost()
        }

        // Scroll to the last item
        composeTestRule.onNodeWithTag("cats_list")
            .performScrollToIndex(7)

        runBlocking {
            delay(10000)
            composeTestRule.onNodeWithTag("catImage").isDisplayed()
        }

    }
}