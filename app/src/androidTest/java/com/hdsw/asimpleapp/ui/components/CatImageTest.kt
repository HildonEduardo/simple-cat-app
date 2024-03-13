package com.hdsw.asimpleapp.ui.components

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hdsw.asimpleapp.MainActivity
import com.hdsw.asimpleapp.data.model.Cat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by hildon.lima<hildon.eduardo></hildon.eduardo>@gmail.com> on 13/03/24.
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CatImageTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val cat = Cat("1", "-", "Cat Owner", listOf("gif"), "-")

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testCatImageThumbnailStyle() {
        composeTestRule.activity.setContent {
            CatImage(
                cat,
                true,
            )
        }

        composeTestRule.onNodeWithTag("catImage").assertExists()
        composeTestRule.onNodeWithTag("catImage").assertWidthIsAtLeast(100.dp)
    }

    fun testCatImageFullStyle() {

        composeTestRule.activity.setContent {
            CatImage(
                cat,
                false,
            )
        }

        composeTestRule.onNodeWithTag("catImage").assertExists()
        composeTestRule.onNodeWithTag("catImage").assertWidthIsAtLeast(400.dp)
    }
}