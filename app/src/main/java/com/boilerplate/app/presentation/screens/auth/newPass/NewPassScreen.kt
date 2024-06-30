package com.boilerplate.app.presentation.screens.auth.newPass

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
import com.boilerplate.app.presentation.components.LoginTextComponent
import com.boilerplate.app.presentation.components.PasswordTextFieldComponent
import com.boilerplate.app.presentation.components.PrimaryButton
import com.boilerplate.app.presentation.components.TextComponent
import com.boilerplate.app.presentation.components.TextSmallTitleComponent
import com.boilerplate.app.presentation.components.VerticalSpacer
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Dimens

@Composable
fun NewPassScreen(
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
                boldText = stringResource(R.string.new_txt),
                italicText = stringResource(R.string.password)
            )

            TextComponent(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colorScheme.onBackgroundGray,
                fontSize = 16.sp,
                value = stringResource(R.string.your_new_password_must_be_different_from_previous_used_password)
            )

            VerticalSpacer(size = 30.dp)

            TextSmallTitleComponent(
                value = stringResource(R.string.password),
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            PasswordTextFieldComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                placeholderText = stringResource(R.string.old_password),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                onTextChanged = {
//                    authViewModel.onSignupEvent(SignupUIEvent.PasswordChanged(it))
                },
//                errorStatus = authViewModel.signupUIState.value.passwordError,
//                errorMessage = authViewModel.signupUIState.value.passwordErrorMsg
            )

            TextSmallTitleComponent(
                value = "Confirm Password",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PasswordTextFieldComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                placeholderText = stringResource(R.string.confirm_password),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                onTextChanged = {
//                    authViewModel.onSignupEvent(SignupUIEvent.ConfirmPasswordChanged(it))
                },
//                errorStatus = authViewModel.signupUIState.value.confirmPasswordError,
//                errorMessage = authViewModel.signupUIState.value.confirmPasswordErrorMsg
            )

            VerticalSpacer()

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp), label = stringResource(R.string.reset_password)
            ) {
//                navController.navigate(NavRoute.OtpVerification.route)
            }
        }
    })

}