package com.boilerplate.app.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

//colors
data class AppColorScheme(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color
)

//typography
data class AppTypography(
    val titleLarge: TextStyle,
    val titleNormal: TextStyle,
    val paragraph: TextStyle,
    val labelLarge: TextStyle,
    val labelNormal: TextStyle,
    val labelSmall: TextStyle
)

//shape
data class AppShape(
    val container: Shape,
    val button: Shape,
    val circular: Shape
)

//size
data class AppSize(
    val large: Dp,
    val medium: Dp,
    val normal: Dp,
    val small: Dp
)

val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleNormal = TextStyle.Default,
        paragraph = TextStyle.Default,
        labelLarge = TextStyle.Default,
        labelNormal = TextStyle.Default,
        labelSmall = TextStyle.Default
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RectangleShape,
        button = RoundedCornerShape(50),
        circular = CircleShape
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        normal = Dp.Unspecified,
        small = Dp.Unspecified
    )
}