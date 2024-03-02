package xyz.torquato.words.ui.views.wordnav.view

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.dynamicfeatures.createGraph
import xyz.torquato.words.ui.views.addword.view.AddWord
import xyz.torquato.words.ui.views.wordlist.view.WordList
import xyz.torquato.words.ui.views.wordnav.model.WordScreen

@Composable
fun WordNav(
    modifier: Modifier = Modifier,
    screen: WordScreen,
    addWord: @Composable () -> Unit,
    wordsList: @Composable () -> Unit,
) {
    val navController = rememberNavController()
    val navGraph = remember(navController) {
        navController.createGraph(startDestination = WordScreen.ADD_WORD.route) {
            composable(route = WordScreen.ADD_WORD.route) { addWord() }
            composable(route = WordScreen.WORDS_LIST.route) { wordsList() }
        }
    }

    NavHost(modifier = modifier, navController = navController, graph = navGraph)
    navController.navigate(screen.route)
}
