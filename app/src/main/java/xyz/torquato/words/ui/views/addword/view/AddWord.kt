package xyz.torquato.words.ui.views.addword.view

import android.widget.ToggleButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
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
        onValueChanged = {input -> viewModel.onValueChanged(input) }
    )
}

@Composable
fun AddWordTemplate(
    modifier: Modifier,
    value: String,
    onValueChanged: (input: String) -> Unit
) {
    var hasNumber: Boolean by remember { mutableStateOf(false)}
    Column {
        Row {
            OutlinedTextField(
                modifier = modifier,
                value = value,
                onValueChange = onValueChanged
            )
            Button(
                onClick = {},
                shape = RectangleShape
            ) {
                Text("+")
            }

        }
        Row {
            Checkbox(checked = hasNumber, onCheckedChange = { hasNumber = !hasNumber })
            Text("has numbers")
        }
        Row {
            Checkbox(checked = hasNumber, onCheckedChange = { hasNumber = !hasNumber })
            Text("has numbers")
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
           onValueChanged = {_ -> }
       )
    }
}