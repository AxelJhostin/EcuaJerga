package com.negocio.ecuajerga.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.negocio.ecuajerga.R

object JergaColors {
    val Background  = Color(0xFF080808)
    val Surface     = Color(0xFF111111)
    val CardBorder  = Color(0x12FFFFFF)
    val TextPrimary = Color(0xFFFFFFFF)
    val TextMuted   = Color(0x55FFFFFF)
}

object JergaFonts {
    val BebasNeue = FontFamily(Font(R.font.bebas_neue, FontWeight.Normal))
    val DmMono    = FontFamily(Font(R.font.dm_mono,    FontWeight.Normal))
}

object JergaType {
    val AppTitle = TextStyle(
        fontFamily    = JergaFonts.BebasNeue,
        fontSize      = 48.sp,
        letterSpacing = 6.sp,
        color         = JergaColors.TextPrimary,
    )
    val SplashWord = TextStyle(
        fontFamily    = JergaFonts.BebasNeue,
        fontSize      = 64.sp,
        letterSpacing = 4.sp,
    )
    val CardLabel = TextStyle(
        fontFamily    = JergaFonts.BebasNeue,
        fontSize      = 16.sp,
        letterSpacing = 2.sp,
    )
    val CardDesc = TextStyle(
        fontFamily    = JergaFonts.DmMono,
        fontSize      = 9.sp,
        letterSpacing = 0.5.sp,
        color         = JergaColors.TextMuted,
    )
    val Tag = TextStyle(
        fontFamily    = JergaFonts.DmMono,
        fontSize      = 10.sp,
        letterSpacing = 4.sp,
        color         = JergaColors.TextMuted,
    )
}

private val DarkColorScheme = darkColorScheme(
    background = JergaColors.Background,
    surface    = JergaColors.Surface,
    onSurface  = JergaColors.TextPrimary,
)

@Composable
fun JergaEcTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content     = content,
    )
}