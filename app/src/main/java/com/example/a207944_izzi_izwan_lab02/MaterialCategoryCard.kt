package com.example.a207944_izzi_izwan_lab02

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a207944_izzi_izwan_lab02.ui.theme.accents

/**
 * A Habbitie-style category card for WeLink (SDG 4).
 *
 * @param title        Bold heading, e.g. "Textbooks".
 * @param hashtag      The word for the bold tag pill, e.g. "Notes" -> shows "#Notes".
 * @param backgroundColor  A pastel from MaterialTheme.accents (coral/orange/lavender/blue/yellow).
 *
 * WHERE TO TWEAK FOR THE Q&A:
 *  - Corner radius        -> the RoundedCornerShape(24.dp) values below.
 *  - Background pastel     -> pass a different MaterialTheme.accents.* at the call site.
 *  - Border thickness/color-> the BorderStroke(...) line.
 */
@Composable
fun MaterialCategoryCard(
    title: String,
    hashtag: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        // High rounded corners = the soft, modern look. Change 24.dp to adjust.
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(0.dp),
        // Thin, clean border around the card.
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Bold, modern heading. onAccent (charcoal) reads cleanly on every pastel.
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.accents.onAccent
            )

            // Bold inline hashtag pill, like the onboarding cards in the reference.
            Text(
                text = "#$hashtag",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.accents.onAccent,
                modifier = Modifier
                    .background(
                        // Semi-transparent white pill so it works on any pastel.
                        color = Color.White.copy(alpha = 0.45f),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            )
        }
    }
}
