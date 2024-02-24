package xyz.torquato.words.ui.views.addword

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import xyz.torquato.words.data.RandomGenerator
import xyz.torquato.words.ui.views.addword.model.AddWordUiState
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    private val randomGenerator: RandomGenerator
): ViewModel() {

    private val _uiState: MutableStateFlow<AddWordUiState> = MutableStateFlow(
        AddWordUiState("")
    )
    init {
        val test = randomGenerator.generateSequence(10)
    }

    val uiState = _uiState.asStateFlow()

    fun onValueChanged(newValue: String) {
        _uiState.update {
            it.copy(
                word = newValue
            )
        }
    }
}