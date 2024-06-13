package com.boilerplate.app.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.boilerplate.app.presentation.components.ImperfectCircleShape

private val darkColorScheme = AppColorScheme(
    background = Black,
    onBackground = White,
    onBackgroundGray = BackgroundLightGray,
    primary = Red,
    onPrimary = White,
    secondary = LightBlack,
    onSecondary = MidLightGray,
    onSecondaryIcon = MidDarkGray,
    workButtonBackground = DarkerRed,
)

private val lightColorScheme = AppColorScheme(
    background = Color.White,
    onBackground = Black,
    onBackgroundGray = BackgroundGray,
    primary = Red,
    onPrimary = White,
    secondary = LighterGray,
    onSecondary = LightGray,
    onSecondaryIcon = DarkGray,
    workButtonBackground = LighterRed,
)

private val typography = AppTypography(
    titleLarge = TextStyle(
        fontFamily = DMSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleNormal = TextStyle(
        fontFamily = DMSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = DMSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    paragraph = TextStyle(
        fontFamily = DMSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = DMSansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    labelNormal = TextStyle(
        fontFamily = DMSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = DMSansFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    )
)

private val shape = AppShape(
    container = RoundedCornerShape(12.dp),
    button = RoundedCornerShape(50),
    circular = ImperfectCircleShape()
)

private val size = AppSize(
    large = 24.dp,
    medium = 16.dp,
    normal = 12.dp,
    small = 8.dp
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme
    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        LocalIndication provides rippleIndication,
        content = content
    )
}

object AppTheme {

    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current
}