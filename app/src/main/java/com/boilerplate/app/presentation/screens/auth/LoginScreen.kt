package com.boilerplate.app.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.boilerplate.app.presentation.composables.PrimaryButton
import com.boilerplate.app.presentation.navigationcomponent.NavRoute
import com.boilerplate.app.theme.AppTheme

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LogInViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
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

//                    navController.navigate(NavRoute.Signup.withArgs(123.toString(), "ali"))
                    navController.navigate(NavRoute.Signup.route)
                }

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp), label = "Login Up"
                ) {
                    loginViewModel.loginRequest.email = "aliabbas2@gmail.com"
                    loginViewModel.loginRequest.password = "password"
                    loginViewModel.onLogInClicked()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
//    LoginScreen()
}