package com.kundutechstudio.theme.Components.TextField


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Comparecomponents.VsBadge
import com.kundutechstudio.theme.ui.AccentBlue
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.AccentRed
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderDefault
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextDisabled
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPlaceholder
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle


/**
 * DevRankTextField — fully custom themed text field
 *
 * @param value         current input text
 * @param onValueChange text change callback
 * @param label         floating label above the field
 * @param placeholder   hint text when empty
 * @param variant       visual style — Default / Search / Password / Success / Error / Disabled
 * @param leadingIcon   optional emoji/icon string shown at left
 * @param trailingIcon  optional emoji/icon string shown at right
 * @param errorMessage  shown below field when variant == Error
 * @param helperText    shown below field for guidance
 * @param singleLine    restrict to one line
 * @param keyboardOptions IME options
 * @param keyboardActions IME action callbacks
 */
@Composable
fun DevRankTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    variant: TextFieldVariant = TextFieldVariant.Default,
    leadingIcon: String? = null,
    trailingIcon: String? = null,
    onTrailingClick: (() -> Unit)? = null,
    errorMessage: String? = null,
    helperText: String? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    enabled: Boolean = variant != TextFieldVariant.Disabled,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    // Password visibility state
    var passwordVisible by remember { mutableStateOf(false) }
    val isPassword = variant == TextFieldVariant.Password
    val visualTransformation = if (isPassword && !passwordVisible)
        PasswordVisualTransformation() else VisualTransformation.None

    // ── Derive colors based on variant + focus ────────────────────
    val borderColor by animateColorAsState(
        targetValue = when {
            !enabled -> BorderMuted
            variant == TextFieldVariant.Error -> AccentRed
            variant == TextFieldVariant.Success -> AccentGreen
            isFocused -> AccentBlue
            else -> BorderDefault
        },
        animationSpec = tween(180),
        label = "borderColor",
    )

    val bgColor = when {
        !enabled -> BgSubtle.copy(alpha = 0.5f)
        isFocused -> BgInset
        else -> BgOverlay
    }

    val labelColor = when {
        !enabled -> TextDisabled
        variant == TextFieldVariant.Error -> AccentRed
        variant == TextFieldVariant.Success -> AccentGreen
        isFocused -> AccentBlueLight
        else -> TextMuted
    }

    val cursorColor = when (variant) {
        TextFieldVariant.Error -> AccentRed
        TextFieldVariant.Success -> AccentGreen
        else -> AccentBlue
    }

    // ── Layout ───────────────────────────────────────────────────
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {

        // ── Label ────────────────────────────────────────────────
        if (label.isNotEmpty()) {
            Text(
                text = label,
                color = labelColor,
                fontSize = 11.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                letterSpacing = 0.04.sp,
                fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
            )
        }

        // ── Field container ──────────────────────────────────────
        BasicTextField(
            value = value,
            onValueChange = { if (enabled) onValueChange(it) },
            enabled = enabled,
            singleLine = singleLine,
            maxLines = if (singleLine) 1 else maxLines,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = if (enabled) TextPrimary else TextDisabled,
                fontSize = 14.sp,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
            ),
            cursorBrush = SolidColor(cursorColor),
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(DevRankShapes.medium)
                        .background(bgColor)
                        .border(BorderWidth.default, borderColor, DevRankShapes.medium)
                        .padding(horizontal = Spacing.lg, vertical = Spacing.md),
                ) {
                    // Leading icon
                    leadingIcon?.let {
                        Text(
                            text = it,
                            fontSize = 16.sp,
                            color = if (isFocused) AccentBlueLight else TextSubtle,
                        )
                    }

                    // Input area
                    Box(modifier = Modifier.weight(1f)) {
                        // Placeholder
                        if (value.isEmpty() && placeholder.isNotEmpty()) {
                            Text(
                                text = placeholder,
                                color = TextPlaceholder,
                                fontSize = 14.sp,
                                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                            )
                        }
                        innerTextField()
                    }

                    // Trailing icon / password toggle / clear
                    when {
                        isPassword -> {
                            Text(
                                text = if (passwordVisible) "🙈" else "👁",
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .let {
                                        if (onTrailingClick != null) it else it
                                    },
                            )
                        }

                        trailingIcon != null -> {
                            Text(
                                text = trailingIcon,
                                fontSize = 16.sp,
                                color = TextSubtle,
                            )
                        }

                        value.isNotEmpty() && variant == TextFieldVariant.Default -> {
                            Text(
                                text = "✕",
                                fontSize = 13.sp,
                                color = TextSubtle,
                                modifier = Modifier.padding(start = 4.dp),
                            )
                        }
                    }

                    // Status icon
                    when (variant) {
                        TextFieldVariant.Success -> Text("✓", fontSize = 14.sp, color = AccentGreen)
                        TextFieldVariant.Error -> Text("!", fontSize = 14.sp, color = AccentRed)
                        else -> Unit
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        // ── Helper / Error text ──────────────────────────────────
        val bottomText = when {
            variant == TextFieldVariant.Error && !errorMessage.isNullOrEmpty() -> errorMessage
            !helperText.isNullOrEmpty() -> helperText
            else -> null
        }
        bottomText?.let {
            Text(
                text = it,
                color = if (variant == TextFieldVariant.Error) AccentRed else TextSubtle,
                fontSize = 11.sp,
                fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                modifier = Modifier.padding(horizontal = 4.dp),
            )
        }
    }
}






@Preview(showBackground = true, backgroundColor = 0xFF0D1117, name = "All Variants")
@Composable
private fun PreviewAllVariants() {
    DevRankTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.lg),
            modifier = Modifier.padding(Spacing.xl),
        ) {
            // Default
            var v1 by remember { mutableStateOf("") }
            DevRankTextField(
                value = v1,
                onValueChange = { v1 = it },
                label = "Default",
                placeholder = "Enter text...",
                leadingIcon = "✏️",
            )

            // Focused with value
            var v2 by remember { mutableStateOf("arjun-kapoor") }
            DevRankTextField(
                value = v2,
                onValueChange = { v2 = it },
                label = "Filled",
                placeholder = "Enter text...",
                leadingIcon = "👤",
            )

            // Success
            DevRankTextField(
                value = "torvalds",
                onValueChange = {},
                label = "Success",
                placeholder = "Enter text...",
                leadingIcon = "@",
                variant = TextFieldVariant.Success,
                helperText = "Username found — Rank #1",
            )

            // Error
            DevRankTextField(
                value = "invalid user!!",
                onValueChange = {},
                label = "Error",
                placeholder = "Enter text...",
                leadingIcon = "@",
                variant = TextFieldVariant.Error,
                errorMessage = "Username contains invalid characters",
            )

            // Disabled
            DevRankTextField(
                value = "readonly-value",
                onValueChange = {},
                label = "Disabled",
                variant = TextFieldVariant.Disabled,
                leadingIcon = "🔒",
            )
        }
    }
}




