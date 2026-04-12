package com.negocio.ecuajerga.model

import androidx.compose.ui.graphics.Color

object SlangRepository {

    val words: List<SlangWord> = listOf(
        SlangWord("huevo",     "El Huevo",   "🥚", Color(0xFFFFE500), "La respuesta clásica"),
        SlangWord("nano",      "Ñaño",       "🤜", Color(0xFFFF6B00), "Hermano / pana"),
        SlangWord("achachay",  "Achachay",   "🥶", Color(0xFF00CFFF), "¡Qué frío!"),
        SlangWord("ayayay",    "Ayayay",     "😖", Color(0xFFFF2D55), "¡Qué dolor!"),
        SlangWord("chiro",     "Chiro",      "💸", Color(0xFF00FF88), "Sin un centavo"),
        SlangWord("bacan",     "Bacán",      "😎", Color(0xFFBF5FFF), "Chévere / cool"),
        SlangWord("tripear",   "Tripear",    "😂", Color(0xFFFF9F0A), "Reírse duro"),
        SlangWord("carishina", "Carishina",  "😤", Color(0xFFFF375F), "Floja / desaplicada"),
        SlangWord("ahorita",   "Ahorita",    "⏳", Color(0xFF32D74B), "...cuando sea"),
        SlangWord("chuta",     "Chuta",      "😬", Color(0xFFFFD60A), "¡Rayos!"),
        SlangWord("longo",     "Longo",      "🧍", Color(0xFFFF6961), "Tonto / bruto"),
        SlangWord("de_ley",    "De Ley",     "✅", Color(0xFF30D5C8), "Claro que sí"),
    )
}