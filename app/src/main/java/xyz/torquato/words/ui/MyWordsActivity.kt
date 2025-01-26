package xyz.torquato.words.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import xyz.torquato.words.ui.theme.WordsTheme
import xyz.torquato.words.ui.views.wordnav.view.WordNav


@AndroidEntryPoint
class MyWordsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordsTheme(darkTheme = true) {
                WordNav()
            }
        }
    }
}