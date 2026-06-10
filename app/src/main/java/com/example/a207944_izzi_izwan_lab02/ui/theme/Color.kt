package com.example.a207944_izzi_izwan_lab02.ui.theme

import androidx.compose.ui.graphics.Color

// =====================================================================
// WeLink design tokens (Habbitie-style). CHANGE A COLOR HERE → it
// updates everywhere, because every screen reads it through the theme.
// =====================================================================

// --- Core neutrals ---
val DeepCharcoal = Color(0xFF1A1A2E) // primary buttons, headings, inputs
val OffWhite     = Color(0xFFF6F6F4) // app background (soft light gray)
val PureWhite    = Color(0xFFFFFFFF) // cards / surfaces sit crisp on OffWhite
val MutedGray    = Color(0xFF6B6B72) // secondary text
val LightBorder  = Color(0xFFE6E6EC) // thin clean borders & dividers
val SurfaceGray  = Color(0xFFEDEDF0) // subtle filled areas (e.g. heat map box)

// --- Vibrant pastel accents (category blocks / onboarding cards) ---
val AccentCoral    = Color(0xFFFF6B6B) // coral / pink
val AccentOrange   = Color(0xFFFF9F1C) // energetic orange
val AccentLavender = Color(0xFFB8A4F0) // soft lavender
val AccentBlue     = Color(0xFF6CC3F0) // sky blue
val AccentYellow   = Color(0xFFFFD43B) // sunny yellow

// Text/icon color that sits on top of the pastels (charcoal reads well on all of them).
val OnAccent = DeepCharcoal

// --- Dark theme neutrals (kept simple; app is light-first) ---
val DarkBackground = Color(0xFF121218)
val DarkSurface    = Color(0xFF1E1E26)
val DarkOnSurface  = Color(0xFFE8E8EC)
