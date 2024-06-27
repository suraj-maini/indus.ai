package com.boilerplate.app.presentation.screens.auth.login

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.presentation.components.LoginTextComponent
import com.boilerplate.app.presentation.components.PasswordTextFieldComponent
import com.boilerplate.app.presentation.components.PrimaryButton
import com.boilerplate.app.presentation.components.PrimaryTextFieldComponent
import com.boilerplate.app.presentation.components.TextButtonComponent
import com.boilerplate.app.presentation.components.TextComponent
import com.boilerplate.app.presentation.components.TextSmallTitleComponent
import com.boilerplate.app.presentation.components.VerticalSpacer
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.presentation.navigationcomponent.NavRoute
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Dimens
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LogInViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var canShowSnackBar = remember { mutableStateOf(false) }
    var snackBarMessage = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(Resource.Failure(FailureStatus.NOTHING)) }
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        onDispose {
            // Cancel the coroutine scope when the Composable is detached
            coroutineScope.cancel()
        }
    }

    LaunchedEffect(key1 = Unit) {
        loginViewModel.initializeState()
        coroutineScope.launch {
            loginViewModel.logInResponse.collect { loginState: LoginUIState ->
                isLoading = loginState.isLoading
                if (loginState.error != null && loginState.error.failureStatus != FailureStatus.NOTHING) {
                    error = loginState.error
                    canShowSnackBar.value = true
                    snackBarMessage.value = loginState.error.message.toString()
                } else {
                    canShowSnackBar.value = false
                    snackBarMessage.value = ""
                }
                loginState.data?.let {
//                    navController.navigate(NavRoute.Home.route)
                    Toast.makeText(context, loginViewModel.getUser().toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    AuthLayout(
        isLogin = true,
        isLoading = isLoading,
        canShowSnackBar = canShowSnackBar,
        snackBarMessage = snackBarMessage,
        onButtonClick1 = error,
        onButtonClick = {
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
                boldText = stringResource(R.string.log),
                italicText = stringResource(R.string.inn)
            )

            TextComponent(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colorScheme.onBackgroundGray,
                value = stringResource(R.string.welcome_to).plus(stringResource(id = R.string.app_name))
            )
            VerticalSpacer(size = 30.dp)

            TextSmallTitleComponent(
                value = stringResource(R.string.email),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PrimaryTextFieldComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                placeholderText = stringResource(id = R.string.email),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onTextChanged = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.emailError,
                errorMessage = loginViewModel.loginUIState.value.emailErrorMsg
            )

            TextSmallTitleComponent(
                value = stringResource(R.string.password),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PasswordTextFieldComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                placeholderText = stringResource(id = R.string.password),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                /*errorStatus = loginViewModel.loginUIState.value.passwordError,*/
                /*errorMessage = loginViewModel.loginUIState.value.passwordErrorMsg*/
            )

            TextButtonComponent(
                modifier = Modifier.align(Alignment.End),
                color = AppTheme.colorScheme.primary,
                value = stringResource(R.string.forgot_password)
            ) {
                navController.navigate(NavRoute.ForgotPassword.route)
            }

            VerticalSpacer()

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                label = stringResource(R.string.login)
            ) {

                loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)

//                loginViewModel.signUpRequest.firstName = "Waqar"
//                loginViewModel.signUpRequest.lastName = "Vicky"
//                loginViewModel.signUpRequest.email = "waqarv713@gmail.com"
//                loginViewModel.signUpRequest.plan = "standard"
//                loginViewModel.signUpRequest.password = "password"
//                loginViewModel.signUpRequest.passwordConfirmation = "password"
//                loginViewModel.onSignUpClicked()

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