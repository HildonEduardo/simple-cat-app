package com.hdsw.asimpleapp.ui.screens

import android.content.res.Configuration
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hdsw.asimpleapp.MainActivity
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.ui.utils.DateUtils
import com.hdsw.asimpleapp.ui.viewmodels.CatDetailViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by hildon.lima</hildon.eduardo>@gmail.com> on 13/03/24.
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CatDetailScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    // Mock the view model
    @BindValue
    @JvmField
    val viewModel = mockk<CatDetailViewModel>(relaxed = true)
    val cat = Cat(
        "1",
        "Wed Jun 01 2022 22:29:22 GMT+0000 (Coordinated UniversalTime)",
        "Cat Owner",
        listOf("tag1", "tag2"),
        "Tue Oct 11 2022 07:52:32 GMT+0000 (Coordinated UniversalTime)"
    )

    @Before
    fun init() {
        hiltRule.inject()
        // Verify the cat detail content
        val catFlow: Flow<Cat> = flowOf(cat)
        every { viewModel.getCat("1") } returns catFlow
    }

    @Test
    fun testCatDetailScreen() {
        val catId = "1"
        composeTestRule.activity.setContent {
            CatDetailScreen(
                catId = catId,
                viewModel = viewModel,
                onBackClicked = { /* Handle back button click */ }
            )
        }

        // Verify the top app bar title
        composeTestRule.onNodeWithText("Cute Cat Detail").assertExists()

        // Verify the back button
        composeTestRule.onNodeWithTag("backButton").assertExists()
        composeTestRule.onNodeWithTag("catImage").assertExists()
        composeTestRule.onNodeWithTag("ownerText").assertExists()
        composeTestRule.onNodeWithText("Tags: tag1, tag2").assertExists()
        composeTestRule.onNodeWithText("Created At: ${DateUtils.formatDate(cat.createdAt)}")
            .assertExists()
        composeTestRule.onNodeWithText("Updated At: ${DateUtils.formatDate(cat.updatedAt)}")
            .assertExists()
    }

    @Test
    fun testCatDetailScreenLandscape() {
        val catId = "1"
        composeTestRule.activity.setContent {
            CatDetailScreen(
                catId = catId,
                viewModel = viewModel,
                onBackClicked = { /* Handle back button click */ },
                orientation = Configuration.ORIENTATION_LANDSCAPE
            )
        }

        //assert landscape layout is being shown
        composeTestRule.onNodeWithTag("catDetailRow").assertExists()

    }
}