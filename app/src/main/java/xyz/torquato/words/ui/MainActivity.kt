package xyz.torquato.words.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import xyz.torquato.words.ui.theme.WordsTheme
import xyz.torquato.words.ui.views.addword.AddWordViewModel
import xyz.torquato.words.ui.views.addword.view.AddWord
import xyz.torquato.words.ui.views.wordlist.WordListViewModel
import xyz.torquato.words.ui.views.wordlist.view.WordList
import xyz.torquato.words.ui.views.wordnav.model.WordScreen
import xyz.torquato.words.ui.views.wordnav.view.WordNav


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val addWordViewModel: AddWordViewModel by viewModels()
    private val wordListViewModel: WordListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf(WordScreen.ADD_WORD) }
            WordsTheme(darkTheme = true) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    WordNav(
                        screen = currentScreen,
                        addWord = {
                            AddWord(
                                viewModel = addWordViewModel,
                                onSendWord = { currentScreen = WordScreen.WORDS_LIST }
                            )
                        },
                        wordsList = {
                            WordList(
                                viewModel = wordListViewModel,
                                onAddWord = { currentScreen = WordScreen.ADD_WORD }
                            )
                        }
                    )
                }
            }
        }
    }
}