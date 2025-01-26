package xyz.torquato.words.ui.views.wordnav

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.torquato.words.data.navidation.NavigationRepository
import xyz.torquato.words.data.navidation.model.WordScreen
import javax.inject.Inject

@HiltViewModel
class WordNavViewModel @Inject constructor(
    private val navigationRepository: NavigationRepository
): ViewModel() {

    val uiState = navigationRepository.currentScreen

    fun navigateToWordList() {
        navigationRepository.setScreen(WordScreen.WORDS_LIST)
    }

    fun navigateToAddWord() {
        navigationRepository.setScreen(WordScreen.ADD_WORD)
    }
}