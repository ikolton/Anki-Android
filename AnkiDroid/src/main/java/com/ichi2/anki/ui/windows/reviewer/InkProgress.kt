// SPDX-License-Identifier: GPL-3.0-or-later

package com.ichi2.anki.ui.windows.reviewer

/** Session progress as on the owner's desktop theme: answered / (answered + left), 0..100. */
fun inkProgressPercent(
    done: Int,
    left: Int,
): Int {
    val d = done.coerceAtLeast(0)
    val l = left.coerceAtLeast(0)
    return 100 * d / maxOf(d + l, 1)
}
