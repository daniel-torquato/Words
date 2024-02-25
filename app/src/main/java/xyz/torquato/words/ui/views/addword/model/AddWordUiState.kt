package xyz.torquato.words.ui.views.addword.model

data class AddWordUiState (
    val word: String,
    val wordLength: String,
    val hasNumbers: Boolean,
    val hasLowerCase: Boolean,
    val hasUpperCase: Boolean,
    val hasSymbols: Boolean,
    val symbolsList: String
)