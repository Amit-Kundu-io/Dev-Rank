package com.kundutechstudio.theme.Components.TextField

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.kundutechstudio.theme.Components.Comparecomponents.VsBadge
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing

/** DevRankUsernameField — username input with @ prefix icon */
@Composable
fun DevRankUsernameField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "GitHub Username",
    errorMessage: String? = null,
    isValid: Boolean = false,
    modifier: Modifier = Modifier,
) {
    DevRankTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = "e.g. torvalds",
        variant = when {
            errorMessage != null -> TextFieldVariant.Error
            isValid -> TextFieldVariant.Success
            else -> TextFieldVariant.Default
        },
        leadingIcon = "@",
        errorMessage = errorMessage,
        helperText = if (errorMessage == null && !isValid) "Enter a valid GitHub username" else null,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
        modifier = modifier,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF0D1117, name = "Compare Input Row")
@Composable
private fun PreviewCompareFields() {
    DevRankTheme {
        Column () {
            var user by remember { mutableStateOf("") }
            DevRankUsernameField(
                value = user,
                onValueChange = { user = it },
            )

            var validUser by remember { mutableStateOf("torvalds") }
            DevRankUsernameField(
                value = validUser,
                onValueChange = { validUser = it },
                isValid = true,
            )

            DevRankUsernameField(
                value = "bad user!!",
                onValueChange = {},
                errorMessage = "Username not found on GitHub",
            )
            var userA by remember { mutableStateOf("torvalds") }
            DevRankUsernameField(
                value = userA,
                onValueChange = { userA = it },
                label = "Developer A",
                isValid = true,
            )
            var userB by remember { mutableStateOf("") }
            DevRankUsernameField(
                value = userB,
                onValueChange = { userB = it },
                label = "Developer B",
            )
        }
    }
}

