package xyz.torquato.words.ui.views.wordlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import xyz.torquato.words.ui.views.wordlist.model.WordListUiState
import javax.inject.Inject

@HiltViewModel
class WordListViewModel @Inject constructor(): ViewModel() {
    private val _uiState: MutableStateFlow<WordListUiState> = MutableStateFlow(WordListUiState(
        emptyList()
    ))

    val uiState = _uiState.asStateFlow()
}