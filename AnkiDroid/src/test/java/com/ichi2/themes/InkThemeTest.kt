// SPDX-License-Identifier: GPL-3.0-or-later

package com.ichi2.themes

import android.content.Intent
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.WindowInsetsControllerCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ichi2.anki.R
import com.ichi2.anki.RobolectricTest
import com.ichi2.anki.StudyOptionsActivity
import com.ichi2.anki.settings.Prefs
import com.ichi2.anki.settings.PrefsRepository
import com.ichi2.anki.settings.enums.AppTheme
import com.ichi2.anki.settings.enums.DayTheme
import com.ichi2.anki.settings.enums.NightTheme
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment

/** The "ink & saffron" overlay from the owner's desktop theme is applied on top of the stock themes. */
@RunWith(AndroidJUnit4::class)
class InkThemeTest : RobolectricTest() {
    @Test
    fun `dark overlay uses ink canvas`() {
        RuntimeEnvironment.setQualifiers("+night")
        PrefsRepository(targetContext).apply {
            appTheme = AppTheme.NIGHT
            nightTheme = NightTheme.DARK
        }
        val activity = startStudyOptions()

        assertThat(activity.color(android.R.attr.colorBackground), equalTo(0xFF12161F.toInt()))
        assertThat(activity.color(androidx.appcompat.R.attr.colorPrimary), equalTo(0xFFE9B35F.toInt()))
        assertThat(activity.color(R.attr.newCountColor), equalTo(0xFF8FB0F2.toInt()))
    }

    @Test
    fun `light overlay uses ink canvas`() {
        PrefsRepository(targetContext).apply {
            appTheme = AppTheme.DAY
            dayTheme = DayTheme.LIGHT
        }
        val activity = startStudyOptions()

        assertThat(activity.color(android.R.attr.colorBackground), equalTo(0xFFF6F7F9.toInt()))
        assertThat(activity.color(androidx.appcompat.R.attr.colorPrimary), equalTo(0xFFA35F00.toInt()))
    }

    @Test
    fun `light theme has dark status and navigation bar icons`() {
        PrefsRepository(targetContext).apply {
            appTheme = AppTheme.DAY
            dayTheme = DayTheme.LIGHT
        }
        // StudyOptionsActivity asks for SystemBarStyle.dark, assuming a dark app bar
        val activity = startStudyOptions()
        val bars = WindowInsetsControllerCompat(activity.window, activity.window.decorView)
        assertThat(bars.isAppearanceLightStatusBars, equalTo(true))
        assertThat(bars.isAppearanceLightNavigationBars, equalTo(true))
    }

    @Test
    fun `dark theme has light status bar icons`() {
        RuntimeEnvironment.setQualifiers("+night")
        PrefsRepository(targetContext).apply {
            appTheme = AppTheme.NIGHT
            nightTheme = NightTheme.DARK
        }
        val activity = startStudyOptions()
        assertThat(WindowInsetsControllerCompat(activity.window, activity.window.decorView).isAppearanceLightStatusBars, equalTo(false))
    }

    @Test
    fun `dark is the default night theme`() {
        // `Prefs` keeps the preferences of the first test that touched it, and other tests here store a night theme
        Prefs.sharedPrefs.edit {
            remove(targetContext.getString(R.string.night_theme_key))
        }
        assertThat(Prefs.nightTheme, equalTo(NightTheme.DARK))
    }

    private fun startStudyOptions() = startActivityNormallyOpenCollectionWithIntent(StudyOptionsActivity::class.java, Intent())

    private fun AppCompatActivity.color(attr: Int): Int {
        val tv = TypedValue()
        theme.resolveAttribute(attr, tv, true)
        return tv.data
    }
}
