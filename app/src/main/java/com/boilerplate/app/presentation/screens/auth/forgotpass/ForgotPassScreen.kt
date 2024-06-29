package com.boilerplate.app.presentation.screens.auth.forgotpass

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.boilerplate.app.R
import com.boilerplate.app.presentation.components.ClickableAuthTextComponent
import com.boilerplate.app.presentation.components.LoginTextComponent
import com.boilerplate.app.presentation.components.PrimaryButton
import com.boilerplate.app.presentation.components.PrimaryTextFieldComponent
import com.boilerplate.app.presentation.components.TextComponent
import com.boilerplate.app.presentation.components.TextSmallTitleComponent
import com.boilerplate.app.presentation.components.VerticalSpacer
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.presentation.navigationcomponent.NavRoute
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Dimens

@Composable
fun ForgotPassScreen(
    navController: NavController
) {
    
    AuthLayout(isLogin = null, onButtonClick = { navController.navigateUp() }, content = {
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
                boldText = stringResource(R.string.recover),
                italicText = stringResource(R.string.password)
            )

            TextComponent(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colorScheme.onBackgroundGray,
                fontSize = 16.sp,
                value = stringResource(R.string.enter_the_email_for_which_you_want_to_recover_your_password)
            )

            VerticalSpacer(size = 30.dp)

            TextSmallTitleComponent(
                value = stringResource(R.string.email),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PrimaryTextFieldComponent(modifier = Modifier
                .fillMaxWidth(),
                placeholderText = stringResource(R.string.email),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done)
            )

            VerticalSpacer()

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp), label = stringResource(R.string.send_recovery_link)
            ) {
                navController.navigate(NavRoute.OtpVerification.route)
            }

            VerticalSpacer()

            ClickableAuthTextComponent(initialText = stringResource(R.string.remember_password), clickAbleText = stringResource(R.string.login), onTextSelected = {
                navController.navigateUp()
            })

        }
    })

}