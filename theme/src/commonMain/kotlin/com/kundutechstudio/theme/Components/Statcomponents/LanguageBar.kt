package com.kundutechstudio.theme.Components.Statcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.CardSize
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.LangCPP
import com.kundutechstudio.theme.ui.LangKotlin
import com.kundutechstudio.theme.ui.LangPython
import com.kundutechstudio.theme.ui.LangTypeScript
import com.kundutechstudio.theme.ui.PillShape
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPrimary

/**
 * LanguageBar — colored segmented bar + legend
 * Used on Profile and Repo Detail screens
 */
data class LanguageSegment(
    val name: String,
    val percent: Float,
    val color: Color,
)

@Composable
fun LanguageBar(
    segments: List<LanguageSegment>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
        // Bar
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(CardSize.langBarHeight)
                .clip(PillShape),
        ) {
            segments.forEach { seg ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(seg.percent)
                        .background(seg.color)
                )
            }
        }
        // Legend
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.lg),
            modifier = Modifier.padding(top = 4.dp),
        ) {
            segments.forEach { seg ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(AvatarShape)
                            .background(seg.color)
                    )
                    Text(
                        text = "${seg.name} ",
                        color = TextMuted,
                        fontSize = 10.sp,
                    )
                    Text(
                        text = "${(seg.percent).toInt()}%",
                        color = TextPrimary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewLanguageBar() {
    DevRankTheme {
        LanguageBar(
            segments = listOf(
                LanguageSegment("Kotlin", 72f, LangKotlin),
                LanguageSegment("TypeScript", 14f, LangTypeScript),
                LanguageSegment("Python", 9f, LangPython),
                LanguageSegment("C++", 5f, LangCPP),
            ),
            modifier = Modifier.padding(Spacing.lg),
        )
    }
}




