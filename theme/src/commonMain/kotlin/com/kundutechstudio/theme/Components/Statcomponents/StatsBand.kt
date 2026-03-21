package com.kundutechstudio.theme.Components.Statcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.kundutechstudio.theme.ui.BgInset
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle


@Composable
fun StatsBand(
    stats: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(DevRankShapes.medium)
            .background(BgInset)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium),
    ) {
        stats.forEachIndexed { index, (value, label) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(CardSize.statBandHeight)
                    .then(
                        if (index > 0)
                            Modifier.border(
                                width = BorderWidth.thin,
                                color = BorderMuted,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(0.dp)
                            )
                        else Modifier
                    ),
            ) {
                Text(
                    text = value,
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                )
                Text(
                    text = label,
                    color = TextSubtle,
                    fontSize = 9.sp,
                    letterSpacing = 0.05.sp,
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewStatsBand() {
    DevRankTheme {
        StatsBand(
            stats = listOf(
                "239K" to "Followers", "11" to "Repos",
                "842K" to "Stars", "41K" to "Commits"
            ),
            modifier = Modifier.padding(Spacing.lg),
        )
    }
}

