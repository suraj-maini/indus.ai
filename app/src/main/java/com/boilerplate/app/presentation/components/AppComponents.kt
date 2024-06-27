package com.boilerplate.app.presentation.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.boilerplate.app.R
import com.boilerplate.app.theme.AppTheme


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTheComposable() {
    PrimaryTextFieldComponent(modifier = Modifier, placeholderText = "Email", onTextChanged = {})
}

@Composable
fun TextComponent(
    modifier: Modifier = Modifier,
    value: String,
    fontSize: TextUnit = 14.sp,
    color: Color = AppTheme.colorScheme.onBackground
) {
    Text(
        text = value,
        modifier = modifier,
        style = AppTheme.typography.labelNormal.copy(
            color = color,
            fontSize = fontSize
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextButtonComponent(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = AppTheme.colorScheme.onBackground,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick.invoke() },
        modifier = modifier
    ) {
        Text(
            text = value,
            style = AppTheme.typography.labelNormal.copy(
                color = color
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TextSmallTitleComponent(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = AppTheme.colorScheme.onBackground
) {
    Text(
        text = value,
        modifier = modifier,
        style = AppTheme.typography.titleSmall.copy(
            color = color
        ),
        textAlign = TextAlign.Start
    )
}

@Composable
fun LoginTextComponent(modifier: Modifier = Modifier, boldText: String, italicText: String) {

    val annotatedString = buildAnnotatedString {
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append("$boldText ")
        pop()
        pushStyle(SpanStyle(fontWeight = FontWeight.Normal, fontStyle = FontStyle.Italic))
        append(italicText)
        pop()
    }

    Text(
        text = annotatedString,
        modifier = modifier,
        style = AppTheme.typography.titleLarge.copy(
            color = AppTheme.colorScheme.onBackground
        ),
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PrimaryTextFieldComponent(
    modifier: Modifier = Modifier,
    placeholderText: String,
    errorStatus: Boolean = false,
    errorMessage: String? = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    onTextChanged: (String) -> Unit = {}
) {

    val textValue = rememberSaveable {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            shape = AppTheme.shape.button,
            placeholder = {
                TextComponent(
                    value = placeholderText,
                    color = AppTheme.colorScheme.onSecondary
                )
            },
            label = null,
            textStyle = LocalTextStyle.current.copy(color = AppTheme.colorScheme.onBackground),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = AppTheme.colorScheme.onBackground,
                focusedTextColor = AppTheme.colorScheme.onBackground,
                unfocusedTextColor = AppTheme.colorScheme.onBackground,
                errorTextColor = AppTheme.colorScheme.primary,
                focusedContainerColor = AppTheme.colorScheme.secondary,
                unfocusedContainerColor = AppTheme.colorScheme.secondary,
                errorContainerColor = AppTheme.colorScheme.secondary,
                disabledContainerColor = AppTheme.colorScheme.secondary
            ),
            keyboardOptions = keyboardOptions,
            singleLine = true,
            maxLines = 1,
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                onTextChanged(it)
            }
        )
        if (!errorStatus) {
            if (!errorMessage.isNullOrEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                    text = errorMessage,
                    style = AppTheme.typography.labelSmall,
                    color = AppTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun PasswordTextFieldComponent(
    modifier: Modifier = Modifier,
    placeholderText: String,
    errorStatus: Boolean = false,
    errorMessage: String? = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    onTextChanged: (String) -> Unit = {}
) {

    val localFocusManager = LocalFocusManager.current
    val password = rememberSaveable {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            shape = AppTheme.shape.button,
            placeholder = {
                TextComponent(
                    value = placeholderText,
                    color = AppTheme.colorScheme.onSecondary
                )
            },
            label = null,
            textStyle = LocalTextStyle.current.copy(color = AppTheme.colorScheme.onBackground),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = AppTheme.colorScheme.onBackground,
                focusedTextColor = AppTheme.colorScheme.onBackground,
                unfocusedTextColor = AppTheme.colorScheme.onBackground,
                errorTextColor = AppTheme.colorScheme.primary,
                focusedContainerColor = AppTheme.colorScheme.secondary,
                unfocusedContainerColor = AppTheme.colorScheme.secondary,
                errorContainerColor = AppTheme.colorScheme.secondary,
                disabledContainerColor = AppTheme.colorScheme.secondary
            ),
            keyboardOptions = keyboardOptions,
            singleLine = true,
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            },
            maxLines = 1,
            value = password.value,
            onValueChange = {
                password.value = it
                onTextChanged(it)
            },
            trailingIcon = {

                val iconImage = if (passwordVisible.value) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }

                val description = if (passwordVisible.value) {
                    stringResource(id = R.string.hide_password)
                } else {
                    stringResource(id = R.string.show_password)
                }

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        imageVector = iconImage, contentDescription = description,
                        tint = AppTheme.colorScheme.onSecondaryIcon
                    )
                }

            },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        )
        if (!errorStatus) {
            if (!errorMessage.isNullOrEmpty())
                Text(
                    modifier = Modifier.fillMaxWidth()
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                    style = AppTheme.typography.labelSmall,
                    text = errorMessage,
                    color = AppTheme.colorScheme.primary
                )
        }
    }
}


@Composable
fun ClickableAuthTextComponent(
    modifier: Modifier = Modifier,
    textSize: TextUnit = 14.sp,
    initialText: String,
    clickAbleText: String,
    onTextSelected: (String) -> Unit
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = AppTheme.typography.labelNormal.copy(
                fontSize = textSize,
                color = AppTheme.colorScheme.onBackgroundGray
            ).toSpanStyle()
        ) {
            pushStringAnnotation(tag = initialText, annotation = initialText)
            append(initialText)
        }
        append(" ")
        withStyle(
            style = AppTheme.typography.labelNormal.copy(
                fontSize = textSize,
                color = AppTheme.colorScheme.primary
            ).toSpanStyle()
        ) {
            pushStringAnnotation(tag = clickAbleText, annotation = clickAbleText)
            append(clickAbleText)
        }
    }

    ClickableText(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = AppTheme.typography.labelNormal.copy(
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == clickAbleText) {
                        onTextSelected(span.item)
                    }
                }

        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicAppBar(
    topAppBarScrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(horizontal = 16.dp),
        colors = TopAppBarColors(
            containerColor = AppTheme.colorScheme.background,
            scrolledContainerColor = AppTheme.colorScheme.onBackground,
            navigationIconContentColor = AppTheme.colorScheme.onBackground,
            actionIconContentColor = AppTheme.colorScheme.onBackground,
            titleContentColor = AppTheme.colorScheme.onBackground
        ),
        title = {
        },
        navigationIcon = {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(110.dp),
                painter = painterResource(id = R.drawable.indus_logo),
                contentDescription = "App logo"
            )
        },
        actions = actions,
        scrollBehavior = topAppBarScrollBehavior
    )
}


@Composable
fun VerticalSpacer(size: Dp = 5.dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun HorizontalSpacer(size: Dp = 5.dp) {
    Spacer(modifier = Modifier.width(size))
}


/*@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DynamicAppBarPreview() {
    DynamicAppBar(
        actions = {
            SecondaryButton(
                label = "Login",
                onClick = { *//* Handle action click *//* })

            SecondaryButton(
                label = "Signup",
                onClick = { *//* Handle action click *//* })

        }
    )
}*/
