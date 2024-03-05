package xyz.torquato.words.ui.views.wordlist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.torquato.words.data.Word
import xyz.torquato.words.ui.theme.WordsTheme
import xyz.torquato.words.ui.views.wordlist.WordListViewModel

@Composable
fun WordList(
    modifier: Modifier = Modifier,
    viewModel: WordListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        WordListTemplate(
            modifier = modifier,
            keys = uiState.keys,
            onDeleteKey = {word -> viewModel.onDeleteWord(word)}
        )
    }
}

@Composable
fun WordListTemplate(
    modifier: Modifier = Modifier,
    keys: List<String>,
    onDeleteKey: (Word) -> Unit
) {

    Column(modifier) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(keys) { key ->
                WordPresenter(key = key, onDeleteKey = { onDeleteKey(Word(key)) })
            }
        }
        AddAction()
    }
}

@Composable
fun WordPresenter(
    key: String,
    onDeleteKey: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .background(Color.Black)
                .weight(1f)
                .height(40.dp)
                .padding(8.dp),
            text = key,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            lineHeight = TextUnit(0.5f, TextUnitType.Em),
            maxLines = 1
        )
        Button(onClick = onDeleteKey, shape = RectangleShape) {
            Icon(Icons.Rounded.Warning, "Remove word")
        }
    }
}

@Composable
fun AddAction(
) {
    FloatingActionButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RectangleShape,
        onClick = { /*TODO*/ },
    ) {
        Icon(
            Icons.Filled.Add,
            "Add new word"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WordsTheme {
        WordListTemplate(keys = listOf("Android", "Jetpack", "Compose"), onDeleteKey = {})
    }
}