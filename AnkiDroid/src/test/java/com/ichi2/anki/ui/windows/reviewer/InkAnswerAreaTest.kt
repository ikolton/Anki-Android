// SPDX-License-Identifier: GPL-3.0-or-later

package com.ichi2.anki.ui.windows.reviewer

import android.content.Context
import android.view.View
import android.view.View.MeasureSpec
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ichi2.anki.databinding.ViewAnswerAreaBinding
import com.ichi2.themes.Themes
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** ink: desktop-style answer buttons, a 2×2 grid with the desktop labels. */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = "pl")
class InkAnswerAreaTest {
    private lateinit var answerArea: AnswerAreaView
    private val binding by lazy { ViewAnswerAreaBinding.bind(answerArea) }

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        Themes.setTheme(context)
        answerArea = AnswerAreaView(context)
    }

    @Test
    fun `labels are desktop labels and come before the interval`() {
        answerArea.setNextTimes(AnswerButtonsNextTime(again = "<1 min", hard = "6 min", good = "4 dni", easy = "9 dni"))
        assertEquals("Jeszcze raz\n<1 min", binding.againButton.text.toString())
        assertEquals("Trudne\n6 min", binding.hardButton.text.toString())
        assertEquals("Dobrze\n4 dni", binding.goodButton.text.toString())
        assertEquals("Łatwe\n9 dni", binding.easyButton.text.toString())
    }

    @Test
    fun `buttons form two rows of two`() {
        layOut()
        assertEquals(binding.againButton.top, binding.hardButton.top)
        assertEquals(binding.goodButton.top, binding.easyButton.top)
        assertTrue("good is below again", binding.goodButton.top > binding.againButton.top)
    }

    @Test
    fun `hide hard and easy leaves again and good in one row`() {
        answerArea.hideHardAndEasyButtons()
        layOut()
        assertEquals(View.GONE, binding.hardButton.visibility)
        assertEquals(View.GONE, binding.easyButton.visibility)
        assertEquals(binding.againButton.top, binding.goodButton.top)
        assertTrue("good is right of again", binding.goodButton.left > binding.againButton.left)
    }

    private fun layOut() {
        answerArea.setAnswerState(isAnswerShown = true)
        answerArea.measure(
            MeasureSpec.makeMeasureSpec(1080, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
        )
        answerArea.layout(0, 0, 1080, answerArea.measuredHeight)
    }
}
