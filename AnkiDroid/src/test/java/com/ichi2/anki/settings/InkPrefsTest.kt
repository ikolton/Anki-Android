// SPDX-License-Identifier: GPL-3.0-or-later

package com.ichi2.anki.settings

import androidx.core.content.edit
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ichi2.anki.R
import com.ichi2.anki.RobolectricTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InkPrefsTest : RobolectricTest() {
    @Test
    fun `new study screen is the default`() {
        // `Prefs` keeps the preferences of the first test that touched it
        Prefs.sharedPrefs.edit { remove(targetContext.getString(R.string.new_reviewer_options_key)) }
        assertThat(Prefs.isNewStudyScreenEnabled, equalTo(true))
    }
}
