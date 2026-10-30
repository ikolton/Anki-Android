// SPDX-License-Identifier: GPL-3.0-or-later

package com.ichi2.anki.ui.windows.reviewer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ichi2.anki.R
import com.ichi2.anki.databinding.ViewStudyCountsBinding

/**
 * ink: shows session progress (answered / answered + left) and the number of cards left,
 * like the owner's desktop theme, instead of the New, Learn and Review counts.
 */
class StudyCountsView : LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val binding = ViewStudyCountsBinding.inflate(LayoutInflater.from(context), this)

    // answers are counted per view instance, so a recreated screen starts the bar from zero
    private var done = 0
    private var left = 0

    fun updateCounts(counts: StudyCounts) {
        left = counts.total
        render()
    }

    fun onAnswered() {
        done++
        render()
    }

    private fun render() {
        binding.progress.setProgressCompat(inkProgressPercent(done, left), true)
        binding.left.text = context.getString(R.string.ink_cards_left, left)
    }
}
