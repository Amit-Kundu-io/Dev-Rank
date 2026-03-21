package com.kundutechstudio.theme.Components.Leaderboardcomponents
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Badgecomponents.RankBadge
import com.kundutechstudio.theme.Components.DevRankAvatar.DevRankAvatar
import com.kundutechstudio.theme.ui.AccentBlueDark
import com.kundutechstudio.theme.ui.AvatarSize
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle

/**
 * SuggestionRow — search suggestion row (user or repo)
 */
@Composable
fun SuggestionRow(
    leading: @Composable () -> Unit,
    title: String,
    subtitle: String,
    trailing: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        modifier              = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = Spacing.xl, vertical = Spacing.md)
            .border(
                width  = BorderWidth.thin,
                color  = BorderMuted,
                shape  = RoundedCornerShape(0.dp)
            ),
    ) {
        leading()
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title,    color = TextPrimary,  fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text(text = subtitle, color = TextSubtle,   fontSize = 10.sp, modifier = Modifier.padding(top = 1.dp))
        }
        trailing?.invoke()
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewSuggestionRows() {
    DevRankTheme {
        Column {
            SuggestionRow(
                leading  = { DevRankAvatar("TL", AvatarSize.sm, AccentBlueDark) },
                title    = "torvalds",
                subtitle = "239K followers · Score: 9,871",
                trailing = { RankBadge(1) },
            )
            SuggestionRow(
                leading  = { Text("🕐", fontSize = 16.sp) },
                title    = "microsoft/vscode",
                subtitle = "Repository · 162K ⭐ TypeScript",
                trailing = { Text("✕", color = TextSubtle, fontSize = 13.sp) },
            )
        }
    }
}



