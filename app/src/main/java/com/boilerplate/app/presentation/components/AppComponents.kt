package com.boilerplate.app.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.boilerplate.app.R
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Black


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTheComposable() {
    PrimaryTextFieldComponent("Email", onTextChanged = {})
}

@Composable
fun TextComponent(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = AppTheme.colorScheme.onBackground
) {
    Text(
        text = value,
        modifier = modifier,
        style = AppTheme.typography.labelNormal.copy(
            color = color
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextButtonComponent(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = AppTheme.colorScheme.onBackground
) {
    TextButton(
        onClick = { /*TODO*/ },
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
        textAlign = TextAlign.Center
    )
}

@Composable
fun LoginTextComponent(modifier: Modifier = Modifier, value: String) {

    val annotatedString = buildAnnotatedString {
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append("Log ")
        pop()
        pushStyle(SpanStyle(fontWeight = FontWeight.Normal, fontStyle = FontStyle.Italic))
        append("In")
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
    placeholderText: String,
    onTextChanged: (String) -> Unit = {},
    errorStatus: Boolean = false
) {

    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        isError = errorStatus
    )
}

@Composable
fun PasswordTextFieldComponent(
    placeholderText: String,
    onTextSelected: (String) -> Unit = {},
    errorStatus: Boolean = false
) {

    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
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
        isError = errorStatus
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