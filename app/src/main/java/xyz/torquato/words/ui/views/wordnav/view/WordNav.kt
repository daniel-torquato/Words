package xyz.torquato.words.ui.views.wordnav.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.dynamicfeatures.createGraph
import xyz.torquato.words.data.navidation.model.WordScreen
import xyz.torquato.words.ui.views.addword.AddWordViewModel
import xyz.torquato.words.ui.views.addword.view.AddWord
import xyz.torquato.words.ui.views.wordlist.WordListViewModel
import xyz.torquato.words.ui.views.wordlist.view.WordList
import xyz.torquato.words.ui.views.wordnav.WordNavViewModel

@Composable
fun WordNavTemplate(
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

@Composable
fun WordNav(
    modifier: Modifier = Modifier,
    wordNavViewModel: WordNavViewModel = viewModel(),
    addWordViewModel: AddWordViewModel = viewModel(),
    wordListViewMode: WordListViewModel = viewModel()
) {
    val currentScreen by wordNavViewModel.uiState.collectAsState()
    Surface(modifier = modifier.fillMaxSize()) {
        WordNavTemplate(
            screen = currentScreen,
            addWord = {
                AddWord(
                    viewModel = addWordViewModel,
                    onSendWord = wordNavViewModel::navigateToWordList
                )
            },
            wordsList = {
                WordList(
                    viewModel = wordListViewMode,
                    onAddWord = wordNavViewModel::navigateToAddWord
                )
            }
        )
    }
}
