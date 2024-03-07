package com.hdsw.asimpleapp.ui.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by hildon.lima<hildon.eduardo></hildon.eduardo>@gmail.com> on 07/03/24.
 */
class DateUtilsTest {

    @Test
    fun formatDate_withValidDate_shouldReturnFormattedDate() {
        val inputDate = "Sun May 01 2022 20:57:11 GMT+0000 (Coordinated Universal Time)"
        val expectedDate = "01 May 2022, 16:57:11" //date considering OS date timezone

        val formattedDate = DateUtils.formatDate(inputDate)

        assertEquals(expectedDate, formattedDate)
    }

    @Test
    fun formatDate_withInvalidDate_shouldReturnEmptyString() {
        val inputDate = "Invalid date string"
        val formattedDate = DateUtils.formatDate(inputDate)

        assertEquals("", formattedDate)
    }

}