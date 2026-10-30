// SPDX-License-Identifier: GPL-3.0-or-later

package com.ichi2.anki.ui.windows.reviewer

import org.junit.Assert.assertEquals
import org.junit.Test

class InkProgressTest {
    @Test
    fun `nothing done is zero`() = assertEquals(0, inkProgressPercent(done = 0, left = 28))

    @Test
    fun `a quarter done`() = assertEquals(25, inkProgressPercent(done = 7, left = 21))

    @Test
    fun `all done is full`() = assertEquals(100, inkProgressPercent(done = 28, left = 0))

    @Test
    fun `empty session is zero`() = assertEquals(0, inkProgressPercent(done = 0, left = 0))

    @Test
    fun `negative done after undo clamps to zero`() = assertEquals(0, inkProgressPercent(done = -1, left = 5))

    @Test
    fun `negative left clamps to full`() = assertEquals(100, inkProgressPercent(done = 5, left = -1))
}
