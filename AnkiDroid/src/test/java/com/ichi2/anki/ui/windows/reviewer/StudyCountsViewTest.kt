// SPDX-License-Identifier: GPL-3.0-or-later

package com.ichi2.anki.ui.windows.reviewer

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ichi2.anki.databinding.ViewStudyCountsBinding
import com.ichi2.themes.Themes
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/** ink: the counters became a progress bar and "N left". */
@RunWith(AndroidJUnit4::class)
class StudyCountsViewTest {
    private lateinit var view: StudyCountsView
    private lateinit var binding: ViewStudyCountsBinding

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        Themes.setTheme(context)
        view = StudyCountsView(context)
        binding = ViewStudyCountsBinding.bind(view)
    }

    @Test
    fun `shows the sum of all queues as cards left`() {
        view.updateCounts(StudyCounts(newCount = 10, learnCount = 5, reviewCount = 20))
        assertEquals("35 left", binding.left.text.toString())
        assertEquals(0, binding.progress.progress)
    }

    @Test
    fun `progress advances with each answer`() {
        view.updateCounts(StudyCounts(newCount = 4))
        view.onAnswered()
        view.updateCounts(StudyCounts(newCount = 3))
        assertEquals(25, binding.progress.progress)
        assertEquals("3 left", binding.left.text.toString())
    }
}
