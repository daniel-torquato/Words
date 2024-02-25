package xyz.torquato.words.ui.views.addword

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import xyz.torquato.words.data.RandomGenerator
import xyz.torquato.words.ui.views.addword.model.AddWordUiState
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    private val randomGenerator: RandomGenerator
) : ViewModel() {

    private val _uiState: MutableStateFlow<AddWordUiState> = MutableStateFlow(
        AddWordUiState("", "10", hasNumbers = true, hasLowerCase = true, symbolsList = "@$[]{}", hasSymbols = true)
    )

    val uiState = _uiState.asStateFlow()

    fun onGenerateTest() {
        val length = _uiState.value.wordLength.toInt()
        val hasNumbers = _uiState.value.hasNumbers
        val hasLowerCase = _uiState.value.hasLowerCase
        val hasSymbols = _uiState.value.hasSymbols
        val symbols = _uiState.value.symbolsList
        if (length > 0)
            onValueChanged(
                randomGenerator.generateSequence(
                    length,
                    hasNumbers,
                    hasLowerCase,
                    hasSymbols,
                    symbols
                )
            )
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