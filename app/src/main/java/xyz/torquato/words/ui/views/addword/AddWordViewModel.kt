package xyz.torquato.words.ui.views.addword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import xyz.torquato.words.data.RandomGenerator
import xyz.torquato.words.data.Word
import xyz.torquato.words.data.WordsRepository
import xyz.torquato.words.ui.views.addword.model.AddWordUiState
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    private val wordsRepository: WordsRepository,
    private val randomGenerator: RandomGenerator,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AddWordUiState> = MutableStateFlow(
        AddWordUiState(
            "",
            "10",
            hasNumbers = true,
            hasLowerCase = true,
            symbolsList = "@$[]{}",
            hasSymbols = true,
            hasUpperCase = true
        )
    )

    val uiState = _uiState.asStateFlow()

    private fun onSaveWord() {
        viewModelScope.launch {
            wordsRepository.insertWord(Word(_uiState.value.word))
        }
    }

    fun onGenerateTest() {
        onValueChanged(
            with(_uiState.value) {
                randomGenerator.generateSequence(
                    size = wordLength.toInt(),
                    hasNumbers = hasNumbers,
                    hasLowerCase = hasLowerCase,
                    hasUpperCase = hasUpperCase,
                    hasSymbols = hasSymbols,
                    symbols = symbolsList,
                )
            }
        )
        onSaveWord()
    }

    fun onValueChanged(newValue: String) {
        _uiState.update {
            it.copy(
                word = newValue
            )
        }
    }

    fun onNumbersChange() {
        _uiState.update {
            it.copy(
                hasNumbers = !it.hasNumbers
            )
        }
    }

    fun onLowerCaseChange() {
        _uiState.update {
            it.copy(
                hasLowerCase = !it.hasLowerCase
            )
        }
    }
    fun onUpperCaseChange(upperCase: Boolean) {
        _uiState.update {
            it.copy(
                hasUpperCase = upperCase
            )
        }
    }

    fun onLengthChange(newLength: String) {
        val pattern = Pattern.compile("\\d+")
        _uiState.update {
            it.copy(
                wordLength =
                if (pattern.matcher(newLength).matches())
                    newLength
                else
                    ""
            )
        }
    }

    fun onLengthIncrement() {
        _uiState.update {
            it.copy(
                wordLength = (it.wordLength.toInt() + 1).toString()
            )
        }
    }

    fun onLengthDecrement() {
        _uiState.update {
            val length = it.wordLength.toInt()
            it.copy(
                wordLength = (if (length > 0) length - 1 else 0).toString()
            )
        }
    }

    fun onSymbolsChange(newSymbols: String) {
        _uiState.update {
            it.copy(
                symbolsList = newSymbols
            )
        }
    }

    fun onHasSymbolsChange(hasSymbols: Boolean) {
        _uiState.update {
            it.copy(
                hasSymbols = hasSymbols
            )
        }
    }
}