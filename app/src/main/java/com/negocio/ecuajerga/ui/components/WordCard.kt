package com.negocio.ecuajerga.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.negocio.ecuajerga.model.SlangWord
import com.negocio.ecuajerga.ui.theme.JergaColors
import com.negocio.ecuajerga.ui.theme.JergaType

private val CardShape = RoundedCornerShape(18.dp)

@Composable
fun WordCard(
    word: SlangWord,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    var pressed by remember { mutableStateOf(false) }
    var glowVisible by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue   = if (pressed) 0.90f else 1f,
        animationSpec = tween(durationMillis = if (pressed) 80 else 200),
        label         = "cardScale",
    )
    val glowAlpha by animateFloatAsState(
        targetValue   = if (glowVisible) 0.35f else 0f,
        animationSpec = tween(300),
        label         = "glowAlpha",
    )

    Box(
        modifier = modifier
            .graphicsLayer { scaleX = scale; scaleY = scale }
            .clip(CardShape)
            .drawBehind {
                if (glowAlpha > 0f)
                    drawRect(color = word.neonColor.copy(alpha = glowAlpha))
            }
            .background(JergaColors.Surface.copy(alpha = 0.6f), CardShape)
            .border(
                width = 1.dp,
                color = if (glowVisible) word.neonColor.copy(alpha = 0.5f) else JergaColors.CardBorder,
                shape = CardShape,
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if (!enabled) return@detectTapGestures
                        pressed = true
                        glowVisible = true
                        tryAwaitRelease()
                        pressed = false
                        glowVisible = false
                        onClick()
                    }
                )
            }
            .padding(vertical = 18.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(text = word.emoji, fontSize = 32.sp)

            Text(
                text      = word.label.uppercase(),
                style     = JergaType.CardLabel.copy(color = word.neonColor),
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
            )

            Text(
                text      = word.desc,
                style     = JergaType.CardDesc,
                textAlign = TextAlign.Center,
                lineHeight = 13.sp,
            )
        }

        NeonCornerAccent(
            color    = word.neonColor,
            modifier = Modifier.align(Alignment.TopStart),
        )
    }
}

@Composable
private fun NeonCornerAccent(color: Color, modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(28.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color.copy(alpha = 0.4f))
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(color.copy(alpha = 0.4f))
        )
    }
}