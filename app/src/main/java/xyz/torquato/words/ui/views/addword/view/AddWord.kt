package xyz.torquato.words.ui.views.addword.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.torquato.words.R
import xyz.torquato.words.ui.theme.WordsTheme
import xyz.torquato.words.ui.utils.lineBelow
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
        onSendWord = {viewModel.onSaveWord() },
        wordLength = uiState.wordLength,
        onLengthChange = { newLength -> viewModel.onLengthChange(newLength) },
        onLengthIncrement = { viewModel.onLengthIncrement() },
        onLengthDecrement = { viewModel.onLengthDecrement() },
        hasNumbers = uiState.hasNumbers,
        onNumbersChange = { viewModel.onNumbersChange() },
        hasLowerCase = uiState.hasLowerCase,
        onLowerCaseChange = { viewModel.onLowerCaseChange() },
        symbolsList = uiState.symbolsList,
        onSymbolsChange = { symbols -> viewModel.onSymbolsChange(symbols) },
        hasSymbols = uiState.hasSymbols,
        onHasSymbolsChange = { hasSymbols -> viewModel.onHasSymbolsChange(hasSymbols) },
        hasUpperCase = uiState.hasUpperCase,
        onUpperCaseChange = { hasUpperCase -> viewModel.onUpperCaseChange(hasUpperCase) }
    )
}

@Composable
fun AddWordTemplate(
    modifier: Modifier,
    value: String,
    onValueChanged: (input: String) -> Unit,
    onGenerateText: () -> Unit,
    onSendWord: () -> Unit,
    wordLength: String,
    onLengthIncrement: () -> Unit,
    onLengthDecrement: () -> Unit,
    onLengthChange: (String) -> Unit,
    hasNumbers: Boolean,
    onNumbersChange: () -> Unit,
    hasLowerCase: Boolean,
    onLowerCaseChange: () -> Unit,
    hasUpperCase: Boolean,
    onUpperCaseChange: (Boolean) -> Unit,
    hasSymbols: Boolean,
    onHasSymbolsChange: (Boolean) -> Unit,
    symbolsList: String,
    onSymbolsChange: (String) -> Unit,
) {
    Column(modifier = modifier.padding(5.dp)) {
        Row {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier
                        .size(36.dp),
                    checked = hasNumbers,
                    onCheckedChange = { onNumbersChange() }
                )
                Text("Numbers")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier
                        .size(36.dp),
                    checked = hasLowerCase,
                    onCheckedChange = { onLowerCaseChange() }
                )
                Text("Lower case")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier
                        .size(36.dp),
                    checked = hasUpperCase,
                    onCheckedChange = { onUpperCaseChange(it) }
                )
                Text("Upper case")
            }
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
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onGenerateText() },
            shape = RectangleShape
        ) {
            Text("Generate word")
        }
        GeneratedWord(
            value = value,
            onValueChanged = onValueChanged,
            onSendWord = onSendWord
        )
    }
}

@Composable
fun GeneratedWord(
    value: String,
    onValueChanged: (String) -> Unit,
    onSendWord: () -> Unit
) {
    val clipBoardManager = LocalClipboardManager.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(onClick = onSendWord) {
            Icon(Icons.Rounded.Send, "Send to list content")
        }
        BasicTextField(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
                .lineBelow(),
            value = value,
            onValueChange = onValueChanged,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                baselineShift = BaselineShift(0.5f),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.None
                )
            )
        )
        IconButton(
            onClick = {
                clipBoardManager.setText(AnnotatedString(value))
            }
        ) {
            Icon(painterResource(id = R.drawable.baseline_content_copy_24), "Copy content")
        }
    }
}

@Composable
fun Symbols(
    value: String,
    onValueChange: (String) -> Unit,
    hasSymbols: Boolean,
    onHasSymbolsChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Symbols: ",
            letterSpacing = TextUnit(1f, TextUnitType(1)),
            maxLines = 1
        )
        BasicTextField(
            modifier = Modifier
                .width(100.dp)
                .lineBelow(),
            value = value,
            singleLine = true,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                baselineShift = BaselineShift(0.3f),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.None
                )
            )
        )
        Checkbox(
            modifier = Modifier.size(36.dp),
            checked = hasSymbols,
            onCheckedChange = { onHasSymbolsChange(it) }
        )
    }
}

@Composable
fun WordLength(
    value: String,
    onValueChange: (String) -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Length: ")
        BasicTextField(
            modifier = Modifier
                .padding(5.dp)
                .width(80.dp)
                .lineBelow(),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                baselineShift = BaselineShift(0.2f)
            )
        )
        FilledIconButton(
            modifier = Modifier
                .padding(2.dp)
                .size(30.dp),
            onClick = { onIncrement() },
            shape = RectangleShape
        ) {
            Icon(Icons.Rounded.Add, "increment action")
        }
        FilledIconButton(
            modifier = Modifier
                .padding(2.dp)
                .size(30.dp),
            onClick = { onDecrement() },
            shape = RectangleShape
        ) {
            Icon(painterResource(id = R.drawable.baseline_remove_24), "decrement action")
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
            onSendWord = {},
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
            onHasSymbolsChange = {},
            hasUpperCase = true,
            onUpperCaseChange = {}
        )
    }
}