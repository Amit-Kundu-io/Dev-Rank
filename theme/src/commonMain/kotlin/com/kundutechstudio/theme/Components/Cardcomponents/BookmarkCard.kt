package com.kundutechstudio.theme.Components.Cardcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kundutechstudio.theme.Components.Badgecomponents.StarChip
import com.kundutechstudio.theme.ui.AvatarShape
import com.kundutechstudio.theme.ui.BgOverlay
import com.kundutechstudio.theme.ui.BgSubtle
import com.kundutechstudio.theme.ui.BorderMuted
import com.kundutechstudio.theme.ui.BorderWidth
import com.kundutechstudio.theme.ui.DevRankShapes
import com.kundutechstudio.theme.ui.DevRankTheme
import com.kundutechstudio.theme.ui.LangKotlin
import com.kundutechstudio.theme.ui.LangTypeScript
import com.kundutechstudio.theme.ui.Spacing
import com.kundutechstudio.theme.ui.TextMuted
import com.kundutechstudio.theme.ui.TextPrimary
import com.kundutechstudio.theme.ui.TextSubtle

/**
 * BookmarkCard — saved repo or developer card in Bookmarks screen
 */
@Composable
fun BookmarkCard(
    name: String,
    meta: String,
    stars: String? = null,
    langColor: Color? = null,
    language: String? = null,
    onRemove: () -> Unit = {},
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        modifier = modifier
            .fillMaxWidth()
            .clip(DevRankShapes.medium)
            .background(BgOverlay)
            .border(BorderWidth.default, BorderMuted, DevRankShapes.medium)
            .clickable(onClick = onClick)
            .padding(Spacing.md),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(DevRankShapes.small)
                .background(BgSubtle),
        ) {
            Text("📦", fontSize = 20.sp)
        }

        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
            ) {
                Text(
                    text = name,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                stars?.let { StarChip(it) }
            }
            Text(text = meta, color = TextMuted, fontSize = 11.sp)
            if (langColor != null && language != null) {
                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(AvatarShape)
                            .background(langColor)
                    )
                    Text(text = language, color = TextSubtle, fontSize = 10.sp)
                }
            }
        }

        Text(
            text = "🔖",
            fontSize = 16.sp,
            modifier = Modifier.clickable(onClick = onRemove),
        )
    }
}




@Preview(showBackground = true, backgroundColor = 0xFF0D1117)
@Composable
private fun PreviewBookmarkCard() {
    DevRankTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
            modifier = Modifier.padding(Spacing.lg),
        ) {
            BookmarkCard(
                name = "microsoft/vscode",
                meta = "TypeScript · Saved 2d ago",
                stars = "162K",
                langColor = LangTypeScript,
                language = "TypeScript",
            )
            BookmarkCard(
                name = "android/sunflower",
                meta = "Kotlin · Saved 5d ago",
                stars = "18K",
                langColor = LangKotlin,
                language = "Kotlin",
            )
        }
    }
}


