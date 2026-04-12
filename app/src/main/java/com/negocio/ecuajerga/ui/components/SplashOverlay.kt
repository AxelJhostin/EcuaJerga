package com.negocio.ecuajerga.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.negocio.ecuajerga.model.SlangWord
import com.negocio.ecuajerga.ui.theme.JergaColors
import com.negocio.ecuajerga.ui.theme.JergaType

@Composable
fun SplashOverlay(
    word: SlangWord,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { visible = true }

    val scale by animateFloatAsState(
        targetValue   = if (visible) 1f else 0.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness    = Spring.StiffnessMedium,
        ),
        label = "splashScale",
    )
    val alpha by animateFloatAsState(
        targetValue   = if (visible) 1f else 0f,
        animationSpec = tween(200),
        label         = "splashAlpha",
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        word.neonColor.copy(alpha = 0.12f),
                        JergaColors.Background.copy(alpha = 0.96f),
                    )
                )
            )
            .clickable(
                indication        = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick           = onDismiss,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            },
        ) {
            // Glow ring + emoji
            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    word.neonColor.copy(alpha = 0.15f),
                                    Color.Transparent,
                                )
                            )
                        )
                )
                Text(text = word.emoji, fontSize = 72.sp)
            }

            // Nombre
            Text(
                text  = word.label.uppercase(),
                style = JergaType.SplashWord.copy(
                    color  = word.neonColor,
                    shadow = Shadow(
                        color      = word.neonColor.copy(alpha = 0.6f),
                        blurRadius = 32f,
                    ),
                ),
                textAlign = TextAlign.Center,
            )

            // Descripción
            Text(
                text      = word.desc,
                style     = JergaType.Tag,
                textAlign = TextAlign.Center,
            )
        }
    }
}