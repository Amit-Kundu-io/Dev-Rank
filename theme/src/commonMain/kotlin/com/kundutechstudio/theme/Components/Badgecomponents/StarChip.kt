package com.kundutechstudio.theme.Components.Badgecomponents


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.StarYellow
import com.kundutechstudio.theme.ui.StarYellowGhost


/**
 * StarChip — "⭐ 383K" gold star count chip
 */
@Composable
fun StarChip(
    count: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        modifier = modifier
            .clip(PillShape)
            .background(StarYellowGhost)
            .padding(horizontal = Spacing.sm, vertical = 3.dp),
    ) {
        Text(text = "★", color = StarYellow, fontSize = 10.sp)
        Text(
            text = count,
            color = StarYellow,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
        )
    }
}


// Previews

@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewPillsAndChips() {
    DevRankTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                StarChip("383K")

            }
        }
    }
}

