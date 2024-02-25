package xyz.torquato.words.ui.views.addword.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.torquato.words.ui.theme.WordsTheme
import xyz.torquato.words.ui.views.addword.AddWordViewModel

@Composable
fun AddWord(
    modifier: Modifier = Modifier,
    viewModel: AddWordViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    AddWordTemplate(
        modifier = modifier,
        value = uiState.word,
        onValueChanged = { input -> viewModel.onValueChanged(input) },
        onGenerateText = { viewModel.onGenerateTest() },
        wordLength = uiState.wordLength,
        onLengthChange = { newLength -> viewModel.onLengthChange(newLength) },
        onLengthIncrement = { viewModel.onLengthIncrement() },
        onLengthDecrement = { viewModel.onLengthDecrement() },
        hasNumbers = uiState.hasNumbers,
        onNumbersChange = { viewModel.onNumbersChange() },
        hasLowerCase = uiState.hasLowerCase,
        onLowerCaseChange = { viewModel.onLowerCaseChange() },
        symbolsList = uiState.symbolsList,
        onSymbolsChange = {symbols -> viewModel.onSymbolsChange(symbols)},
        hasSymbols = uiState.hasSymbols,
        onHasSymbolsChange = {hasSymbols -> viewModel.onHasSymbolsChange(hasSymbols)}
    )
}

@Composable
fun AddWordTemplate(
    modifier: Modifier,
    value: String,
    onValueChanged: (input: String) -> Unit,
    onGenerateText: () -> Unit,
    wordLength: String,
    onLengthIncrement: () -> Unit,
    onLengthDecrement: () -> Unit,
    onLengthChange: (String) -> Unit,
    hasNumbers: Boolean,
    onNumbersChange: () -> Unit,
    hasLowerCase: Boolean,
    onLowerCaseChange: () -> Unit,
    hasSymbols: Boolean,
    onHasSymbolsChange: (Boolean) -> Unit,
    symbolsList: String,
    onSymbolsChange: (String) -> Unit,
) {
    Column {
        Row {
            OutlinedTextField(
                modifier = modifier,
                value = value,
                onValueChange = onValueChanged
            )
            Button(
                onClick = { onGenerateText() },
                shape = RectangleShape
            ) {
                Text("+")
            }

        }
        Row {
            Checkbox(checked = hasNumbers, onCheckedChange = { onNumbersChange() })
            Text("has numbers")
        }
        Row {
            Checkbox(checked = hasLowerCase, onCheckedChange = { onLowerCaseChange() })
            Text("has lower case")
        }
        Symbols(
            value = symbolsList,
            onValueChange = onSymbolsChange,
            hasSymbols = hasSymbols,
            onHasSymbolsChange = onHasSymbolsChange
        )
        WordLength(
            wordLength,
            onIncrement = onLengthIncrement,
            onDecrement = onLengthDecrement,
            onValueChange = onLengthChange
        )
    }

}

@Composable
fun Symbols(
    value: String,
    onValueChange: (String) -> Unit,
    hasSymbols: Boolean,
    onHasSymbolsChange: (Boolean) -> Unit
) {
    Row {
        Text("Symbols: ")
        TextField(
            modifier = Modifier.width(100.dp),
            value = value,
            singleLine = true,
            onValueChange = onValueChange
        )
        Checkbox(checked = hasSymbols, onCheckedChange = { onHasSymbolsChange(it) })
    }
}

@Composable
fun WordLength(
    value: String,
    onValueChange: (String) -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    Row {
        Text("Length: ")
        TextField(
            modifier = Modifier.width(80.dp),
            value = value,
            singleLine = true,
            onValueChange = onValueChange
        )
        Button(
            onClick = { onIncrement() },
            shape = RectangleShape
        ) {
            Text("+")
        }
        Button(
            onClick = { onDecrement() },
            shape = RectangleShape
        ) {
            Text("-")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddWordPreview() {
    WordsTheme {
        AddWordTemplate(
            modifier = Modifier,
            value = "Word",
            onValueChanged = { _ -> },
            onGenerateText = {},
            wordLength = "10",
            onLengthIncrement = {},
            onLengthDecrement = {},
            onLengthChange = {},
            hasNumbers = true,
            onNumbersChange = {},
            hasLowerCase = true,
            onLowerCaseChange = {},
            symbolsList = "@$[]{}",
            onSymbolsChange = {},
            hasSymbols = true,
            onHasSymbolsChange = {}
        )
    }
}