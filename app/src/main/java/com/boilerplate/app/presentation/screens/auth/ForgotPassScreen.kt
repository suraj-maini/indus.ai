package com.boilerplate.app.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.boilerplate.app.presentation.components.ClickableAuthTextComponent
import com.boilerplate.app.presentation.components.LoginTextComponent
import com.boilerplate.app.presentation.components.PrimaryButton
import com.boilerplate.app.presentation.components.PrimaryTextFieldComponent
import com.boilerplate.app.presentation.components.TextSmallTitleComponent
import com.boilerplate.app.presentation.components.VerticalSpacer
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Dimens

@Composable
fun ForgotPassScreen(
    navController: NavController
) {
    
    AuthLayout(isLogin = null, onButtonClick = { navController.navigateUp() }) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(Dimens.defaultScreenPadding)
                .background(AppTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            LoginTextComponent(
                modifier = Modifier.fillMaxWidth(),
                boldText = "Recover",
                italicText = "Password"
            )

            VerticalSpacer(size = 30.dp)

            TextSmallTitleComponent(
                value = "Email",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PrimaryTextFieldComponent(modifier = Modifier
                .fillMaxWidth(),
                placeholderText = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done)
            )

            VerticalSpacer()

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp), label = "Send recovery link"
            ) {

            }

            VerticalSpacer()

            ClickableAuthTextComponent(initialText = "Remember password?", clickAbleText = "Login", onTextSelected = {
                navController.navigateUp()
            })

        }
    }
    
}