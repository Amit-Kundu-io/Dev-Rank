package com.kundutechstudio.theme.Components.Leaderboardcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.BorderDefault
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPlaceholder

/**
 * DotsSeparator — "· · · 2,445 more · · ·" between list items
 */
@Composable
fun DotsSeparator(label: String = "", modifier: Modifier = Modifier) {
    Row(
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier              = modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.sm),
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(AvatarShape)
                    .background(BorderDefault)
            )
            Spacer(Modifier.width(4.dp))
        }
        if (label.isNotEmpty()) {
            Text(
                text     = label,
                color    = TextPlaceholder,
                fontSize = 10.sp,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                modifier = Modifier.padding(horizontal = Spacing.sm),
            )
            repeat(3) {
                Spacer(Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .clip(AvatarShape)
                        .background(BorderDefault)
                )
            }
        }
    }
}



@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewDotsSeparator() {
    DevRankTheme {
        DotsSeparator("2,445 more", Modifier.padding(Spacing.lg))
    }
}

