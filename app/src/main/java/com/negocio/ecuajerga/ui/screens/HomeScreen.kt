package com.negocio.ecuajerga.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.negocio.ecuajerga.ui.components.SplashOverlay
import com.negocio.ecuajerga.ui.components.WordCard
import com.negocio.ecuajerga.ui.theme.JergaColors
import com.negocio.ecuajerga.ui.theme.JergaType
import com.negocio.ecuajerga.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(JergaColors.Background)
                .systemBarsPadding()
                .padding(horizontal = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(24.dp))

            HomeHeader()

            Spacer(Modifier.height(8.dp))

            // Indicador de carga
            AnimatedVisibility(
                visible = !uiState.soundsReady,
                enter   = fadeIn(),
                exit    = fadeOut(),
            ) {
                Text(
                    text      = "• CARGANDO SONIDOS •",
                    style     = JergaType.Tag.copy(color = JergaColors.TextMuted),
                    textAlign = TextAlign.Center,
                    modifier  = Modifier.padding(bottom = 8.dp),
                )
            }

            Spacer(Modifier.height(8.dp))

            LazyVerticalGrid(
                columns               = GridCells.Fixed(3),
                verticalArrangement   = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding        = PaddingValues(bottom = 24.dp),
                modifier              = Modifier.fillMaxWidth(),
            ) {
                items(
                    items = uiState.words,
                    key   = { it.id },
                ) { word ->
                    WordCard(
                        word     = word,
                        onClick  = { viewModel.onWordTapped(word) },
                        enabled  = uiState.soundsReady,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.82f),
                    )
                }
            }
        }

        // Splash overlay
        AnimatedVisibility(
            visible  = uiState.activeSplash != null,
            enter    = fadeIn(),
            exit     = fadeOut(),
            modifier = Modifier.fillMaxSize(),
        ) {
            uiState.activeSplash?.let { word ->
                SplashOverlay(
                    word      = word,
                    onDismiss = { viewModel.dismissSplash() },
                )
            }
        }
    }
}

@Composable
private fun HomeHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text  = "🇪🇨   ECUADOR",
            style = JergaType.Tag,
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text  = "JERGA EC",
            style = JergaType.AppTitle,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text      = "TOCA Y TRIPEA CON TUS AMIGOS",
            style     = JergaType.Tag,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(14.dp))

        HeaderDivider()
    }
}

@Composable
private fun HeaderDivider() {
    Row(
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color.Transparent, JergaColors.CardBorder)
                    )
                )
        )
        Spacer(Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(JergaColors.CardBorder, shape = CircleShape)
        )
        Spacer(Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(JergaColors.CardBorder, Color.Transparent)
                    )
                )
        )
    }
}