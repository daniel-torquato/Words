package xyz.torquato.words.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import xyz.torquato.words.ui.theme.WordsTheme
import xyz.torquato.words.ui.views.addword.AddWordViewModel
import xyz.torquato.words.ui.views.addword.view.AddWord
import xyz.torquato.words.ui.views.wordnav.view.WordNav


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val exampleViewModel: AddWordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordsTheme {
                //WordList(viewModel = viewModel())
                AddWord(
                    viewModel = exampleViewModel
                )
            }
        }
    }
}