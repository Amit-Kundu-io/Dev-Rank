package com.kundutechstudio.theme.Components.TextField

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing

/** DevRankSearchField — search bar with search icon, keyboard Search IME */
@Composable
fun DevRankSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Search developers & repos...",
    modifier: Modifier = Modifier,
) {
    DevRankTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        variant = TextFieldVariant.Search,
        leadingIcon = "🔍",
        trailingIcon = if (value.isNotEmpty()) "✕" else null,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        modifier = modifier,
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117, name = "Search Field")
@Composable
private fun PreviewSearchField() {
    DevRankTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.md),
            modifier = Modifier.padding(Spacing.xl),
        ) {
            var q1 by remember { mutableStateOf("") }
            DevRankSearchField(value = q1, onValueChange = { q1 = it })

            var q2 by remember { mutableStateOf("torval") }
            DevRankSearchField(value = q2, onValueChange = { q2 = it })
        }
    }
}



