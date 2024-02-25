package xyz.torquato.words.ui.views.addword.model

data class AddWordUiState (
    val word: String,
    val wordLength: String,
    val hasNumbers: Boolean,
    val hasLowerCase: Boolean
)