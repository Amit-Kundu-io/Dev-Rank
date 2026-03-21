package com.kundutechstudio.theme.Components.Statcomponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AccentBlueLight
import com.kundutechstudio.theme.ui.AccentGreen
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPrimary

/**
 * InfoRow — label : value row used in Repo Detail info section
 */
@Composable
fun InfoRow(
    label: String,
    value: String,
    valueColor: Color = TextPrimary,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.xl, vertical = 11.dp),
    ) {
        Text(text = label, color = TextMuted, fontSize = 12.sp)
        Text(
            text = value, color = valueColor, fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewInfoRows() {
    DevRankTheme {
        Column(
            modifier = Modifier.border(
                BorderWidth.thin, BorderMuted,
                androidx.compose.foundation.shape.RoundedCornerShape(0.dp)
            )
        ) {
            InfoRow("Primary Language", "JavaScript", AccentBlueLight)
            InfoRow("License", "MIT License")
            InfoRow("Last Commit", "2 hours ago", AccentGreen)
            InfoRow("Open Issues", "287,434")
        }
    }
}

