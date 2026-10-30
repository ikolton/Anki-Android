// SPDX-License-Identifier: GPL-3.0-or-later

package com.ichi2.anki.ui.windows.reviewer

import android.content.Context
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.AttributeSet
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.google.android.material.button.MaterialButton
import com.google.android.material.color.MaterialColors
import com.ichi2.anki.R
import com.ichi2.anki.utils.ext.usingStyledAttributes
import com.google.android.material.R as MaterialR

class AnswerButton : MaterialButton {
    private val easeName: String

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, com.google.android.material.R.attr.materialButtonStyle)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        easeName =
            context.usingStyledAttributes(attrs, R.styleable.AnswerButton) {
                requireNotNull(getString(R.styleable.AnswerButton_easeName)) {
                    "app:easeName value not set"
                }
            }

        val nextTime =
            context.usingStyledAttributes(attrs, R.styleable.AnswerButton) {
                getString(R.styleable.AnswerButton_nextTime)
            }

        setNextTime(nextTime)
    }

    fun setNextTime(nextTime: String?) {
        text =
            if (nextTime != null) {
                // ink: label first, the interval below it, smaller and muted, as on the desktop
                buildSpannedString {
                    append(easeName)
                    append("\n")
                    inSpans(
                        RelativeSizeSpan(0.8F),
                        ForegroundColorSpan(MaterialColors.getColor(this@AnswerButton, MaterialR.attr.colorOnSurfaceVariant)),
                    ) {
                        append(nextTime)
                    }
                }
            } else {
                easeName
            }
    }
}
