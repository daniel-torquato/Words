package xyz.torquato.words.ui.views.addword

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import xyz.torquato.words.ui.views.addword.model.AddWordUiState
import xyz.torquato.words.ui.views.wordlist.model.WordListUiState

@HiltViewModel
class AddWordViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<AddWordUiState> = MutableStateFlow(
        AddWordUiState("")
    )

    val uiState = _uiState.asStateFlow()

    fun onValueChanged(newValue: String) {
        _uiState.update {
            it.copy(
                word = newValue
            )
        }
    }
}