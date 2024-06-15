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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.boilerplate.app.R
import com.boilerplate.app.presentation.components.*
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.presentation.navigationcomponent.NavRoute
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Dimens

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LogInViewModel = hiltViewModel()
) {
    AuthLayout(isLogin = true, onButtonClick = {
        navController.navigate(NavRoute.Signup.withArgs(123.toString(), "ali"))
    }) {
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
                boldText = "Login",
                italicText = "In"
            )

            TextComponent(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colorScheme.onBackgroundGray,
                value = "Welcome to ".plus(stringResource(id = R.string.app_name))
            )
            VerticalSpacer(size = 30.dp)

            TextSmallTitleComponent(
                value = "Email",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PrimaryTextFieldComponent(modifier = Modifier
                .fillMaxWidth(),
                placeholderText = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
            )

            TextSmallTitleComponent(
                value = "Password",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PasswordTextFieldComponent("Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done))

            TextButtonComponent(
                modifier = Modifier.align(Alignment.End),
                color = AppTheme.colorScheme.primary,
                value = "Forgot Password?"
            ){
                navController.navigate(NavRoute.ForgotPassword.route)
            }

            VerticalSpacer()

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp), label = "Login"
            ) {
                loginViewModel.signUpRequest.firstName = "Waqar"
                loginViewModel.signUpRequest.lastName = "Vicky"
                loginViewModel.signUpRequest.email = "waqarv713@gmail.com"
                loginViewModel.signUpRequest.plan = "standard"
                loginViewModel.signUpRequest.password = "password"
                loginViewModel.signUpRequest.passwordConfirmation = "password"
                loginViewModel.onSignUpClicked()

//                navController.navigate(NavRoute.Signup.withArgs(123.toString(), "ali"))
//                    navController.navigate(NavRoute.Signup.route)
            }

            /*SecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp), label = "Login"
            ) {
                loginViewModel.loginRequest.email = "aliabbas2@gmail.com"
                loginViewModel.loginRequest.password = "password"
                loginViewModel.onLogInClicked()
            }*/
        }
    }

    /*Box(
        modifier = Modifier.fillMaxSize()
    ) {

        DynamicAppBar(
            actions = {
                SecondaryButton(
                    label = "Login",
                    onClick = { *//* Handle action click *//* })
                Spacer(modifier = Modifier.width(7.dp))
                SecondaryButton(
                    label = "Signup",
                    onClick = { *//* Handle action click *//* })

            }
        )

        *//*Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                TextComponent(
                    value = "This is a text composable"
                )

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp), label = "Sign Up"
                ) {
                    loginViewModel.signUpRequest.firstName = "Waqar"
                    loginViewModel.signUpRequest.lastName = "Vicky"
                    loginViewModel.signUpRequest.email = "waqarv713@gmail.com"
                    loginViewModel.signUpRequest.plan = "standard"
                    loginViewModel.signUpRequest.password = "password"
                    loginViewModel.signUpRequest.passwordConfirmation = "password"
                    loginViewModel.onSignUpClicked()

                    navController.navigate(NavRoute.Signup.withArgs(123.toString(), "ali"))
//                    navController.navigate(NavRoute.Signup.route)
                }

                SecondaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp), label = "Login"
                ) {
                    loginViewModel.loginRequest.email = "aliabbas2@gmail.com"
                    loginViewModel.loginRequest.password = "password"
                    loginViewModel.onLogInClicked()
                }
            }
        }*//*
    }*/
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(navController = NavController(LocalContext.current))
    }
}