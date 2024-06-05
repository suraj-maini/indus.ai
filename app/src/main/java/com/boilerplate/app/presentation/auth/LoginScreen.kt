package com.boilerplate.app.presentation.auth

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.boilerplate.app.presentation.composables.PrimaryButton
import com.boilerplate.app.theme.AppTheme

@Composable
fun LoginScreen(loginViewModel: LogInViewModel = hiltViewModel()) {

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
                    .background(AppTheme.colorScheme.background)
            ) {
                PrimaryButton(modifier = Modifier.fillMaxWidth()
                    .height(45.dp), label = "Sign Up") {
                     loginViewModel.request.email = "aliabbas2@gmail.com"
                     loginViewModel.request.password = "password"
                     loginViewModel.onLogInClicked()
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}