package xyz.torquato.words.ui.views.wordnav.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.dynamicfeatures.createGraph
import xyz.torquato.words.ui.views.addword.view.AddWord
import xyz.torquato.words.ui.views.wordlist.view.WordList

@Composable
fun WordNav(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navGraph = remember(navController) {
        navController.createGraph(startDestination = "addWord") {
            composable("addWord") { AddWord() }
            composable("wordList") { WordList() }
        }
    }

    NavHost(navController, navGraph)
}
