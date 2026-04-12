package com.negocio.ecuajerga.model

import androidx.compose.ui.graphics.Color

data class SlangWord(
    val id: String,
    val label: String,
    val emoji: String,
    val neonColor: Color,
    val desc: String,
    val audioCount: Int = 2, // cuántas variantes de audio tiene
)