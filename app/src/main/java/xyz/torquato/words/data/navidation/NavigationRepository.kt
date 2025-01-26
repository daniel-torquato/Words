package xyz.torquato.words.data.navidation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import xyz.torquato.words.data.navidation.model.WordScreen
import javax.inject.Inject

class NavigationRepository @Inject constructor() {

    private val _currentScreen = MutableStateFlow(WordScreen.ADD_WORD)

    val currentScreen = _currentScreen.asStateFlow()

    fun setScreen(screen: WordScreen) {
        _currentScreen.value = screen
    }
}