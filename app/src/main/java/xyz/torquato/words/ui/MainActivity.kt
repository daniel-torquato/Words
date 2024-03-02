package xyz.torquato.words.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
            WordsTheme {
                WordNav(
                    screen = WordScreen.ADD_WORD,
                    addWord = {
                        AddWord(
                            viewModel = addWordViewModel
                        )
                    },
                    wordsList = {
                        WordList(
                            viewModel = wordListViewModel
                        )
                    }
                )
            }
        }
    }
}