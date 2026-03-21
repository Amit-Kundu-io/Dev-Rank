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

/** DevRankPasswordField — password input with show/hide toggle */
@Composable
fun DevRankPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Password",
    placeholder: String = "Enter password",
    errorMessage: String? = null,
    modifier: Modifier = Modifier,
) {
    DevRankTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        variant = if (errorMessage != null) TextFieldVariant.Error else TextFieldVariant.Password,
        leadingIcon = "🔒",
        errorMessage = errorMessage,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        modifier = modifier,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117, name = "Username + Password")
@Composable
private fun PreviewUsernamePassword() {
    DevRankTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.lg),
            modifier = Modifier.padding(Spacing.xl),
        ) {


            var pass by remember { mutableStateOf("") }
            DevRankPasswordField(
                value = pass,
                onValueChange = { pass = it },
            )

            DevRankPasswordField(
                value = "wrongpass",
                onValueChange = {},
                errorMessage = "Incorrect password",
            )
        }
    }
}




