package com.example.a207944_izzi_izwan_lab02.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// =====================================================================
// 1. MATERIAL 3 COLOR SCHEME
//    Maps our design tokens to the standard Material slots. Existing
//    screens that call MaterialTheme.colorScheme.* automatically adopt
//    the new look. The pastel containers feed the Home category cards.
// =====================================================================
private val LightColors = lightColorScheme(
    primary = DeepCharcoal,          // primary buttons, FAB, active text
    onPrimary = PureWhite,           // text/icons on top of primary
    secondary = DeepCharcoal,
    onSecondary = PureWhite,
    background = OffWhite,            // app background
    onBackground = DeepCharcoal,
    surface = PureWhite,             // cards sit crisp white on the off-white bg
    onSurface = DeepCharcoal,
    surfaceVariant = SurfaceGray,    // subtle filled boxes
    onSurfaceVariant = MutedGray,    // secondary/caption text
    outline = LightBorder,           // thin clean borders
    outlineVariant = LightBorder,
    // The four "container" slots are wired to the pastels so the existing
    // category cards instantly pick up the coral/blue/lavender/yellow look.
    primaryContainer = AccentBlue,
    onPrimaryContainer = OnAccent,
    secondaryContainer = AccentLavender,
    onSecondaryContainer = OnAccent,
    tertiaryContainer = AccentYellow,
    onTertiaryContainer = OnAccent,
    error = AccentCoral,
    onError = PureWhite,
    errorContainer = AccentCoral,
    onErrorContainer = OnAccent,
)

private val DarkColors = darkColorScheme(
    primary = PureWhite,
    onPrimary = DeepCharcoal,
    background = DarkBackground,
    onBackground = DarkOnSurface,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = Color(0xFF2A2A33),
    onSurfaceVariant = Color(0xFFB5B5BE),
    outline = Color(0xFF3A3A44),
    primaryContainer = AccentBlue,
    onPrimaryContainer = OnAccent,
    secondaryContainer = AccentLavender,
    onSecondaryContainer = OnAccent,
    tertiaryContainer = AccentYellow,
    onTertiaryContainer = OnAccent,
    error = AccentCoral,
    onError = PureWhite,
    errorContainer = AccentCoral,
    onErrorContainer = OnAccent,
)

// =====================================================================
// 2. EXTENDED ACCENT PALETTE
//    Material's scheme has no slot for "5 named pastels", so we add our
//    own. Access it anywhere with: MaterialTheme.accents.coral, etc.
// =====================================================================
@Immutable
data class WeLinkAccents(
    val coral: Color,
    val orange: Color,
    val lavender: Color,
    val blue: Color,
    val yellow: Color,
    val onAccent: Color
)

private val DefaultAccents = WeLinkAccents(
    coral = AccentCoral,
    orange = AccentOrange,
    lavender = AccentLavender,
    blue = AccentBlue,
    yellow = AccentYellow,
    onAccent = OnAccent
)

// CompositionLocal carries the accents down the UI tree so any composable can read them.
private val LocalWeLinkAccents = staticCompositionLocalOf { DefaultAccents }

// Convenience accessor: MaterialTheme.accents.lavender
val MaterialTheme.accents: WeLinkAccents
    @Composable
    @ReadOnlyComposable
    get() = LocalWeLinkAccents.current

// =====================================================================
// 3. THEME WRAPPER (same name MainActivity already calls)
// =====================================================================
@Composable
fun A207944_Izzi_Izwan_Lab03Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Off by default so WeLink's brand colors always show (not the system wallpaper colors).
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Match the status bar to the background; use dark icons on the light theme.
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    // Provide the accents alongside the standard MaterialTheme.
    CompositionLocalProvider(LocalWeLinkAccents provides DefaultAccents) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
