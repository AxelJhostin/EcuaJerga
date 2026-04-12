package com.negocio.ecuajerga

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.negocio.ecuajerga.ui.screens.HomeScreen
import com.negocio.ecuajerga.ui.theme.JergaEcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JergaEcTheme {
                HomeScreen()
            }
        }
    }
}