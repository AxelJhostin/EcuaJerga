package com.negocio.ecuajerga.viewmodel

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.negocio.ecuajerga.model.SlangRepository
import com.negocio.ecuajerga.model.SlangWord
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val words: List<SlangWord> = SlangRepository.words,
    val activeSplash: SlangWord? = null,
    val soundsReady: Boolean = true, // MediaPlayer no necesita precarga
)

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var currentPlayer: MediaPlayer? = null

    fun onWordTapped(word: SlangWord) {
        playSound(word)
        showSplash(word)
    }

    private fun playSound(word: SlangWord) {
        val context = getApplication<Application>()
        val res = context.resources
        val pkg = context.packageName

        // Elige variante aleatoria entre las disponibles
        val variant = (1..word.audioCount).random()
        val resName = "${word.id}_$variant"
        val resId = res.getIdentifier(resName, "raw", pkg)

        if (resId == 0) return // archivo no existe aún, no hace nada

        // Detiene el audio anterior si aún está sonando
        currentPlayer?.apply {
            if (isPlaying) stop()
            release()
        }

        currentPlayer = MediaPlayer.create(context, resId)?.apply {
            setOnCompletionListener { release() }
            start()
        }
    }

    private fun showSplash(word: SlangWord) {
        _uiState.value = _uiState.value.copy(activeSplash = word)
        viewModelScope.launch {
            delay(SPLASH_DURATION_MS)
            dismissSplash()
        }
    }

    fun dismissSplash() {
        _uiState.value = _uiState.value.copy(activeSplash = null)
    }

    override fun onCleared() {
        super.onCleared()
        currentPlayer?.release()
    }

    companion object {
        private const val SPLASH_DURATION_MS = 1800L
    }
}