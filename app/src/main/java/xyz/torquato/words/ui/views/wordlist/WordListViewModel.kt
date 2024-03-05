package xyz.torquato.words.ui.views.wordlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import xyz.torquato.words.data.Word
import xyz.torquato.words.data.WordsRepository
import xyz.torquato.words.ui.views.wordlist.model.WordListUiState
import javax.inject.Inject

@HiltViewModel
class WordListViewModel @Inject constructor(
    private val wordsRepository: WordsRepository,
): ViewModel() {
    val uiState = wordsRepository.getAllItemsStream().map { words ->
        Log.d("MyTag", "List $words")
        WordListUiState(words.map { it.word })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = WordListUiState(emptyList())
    )

    fun onDeleteWord(word: Word) {
        viewModelScope.launch {
            wordsRepository.deleteWord(word)
        }
    }
}