package com.boilerplate.app.presentation.screens.auth.forgotpass

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.boilerplate.app.R
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.presentation.components.ClickableAuthTextComponent
import com.boilerplate.app.presentation.components.LoginTextComponent
import com.boilerplate.app.presentation.components.PrimaryButton
import com.boilerplate.app.presentation.components.PrimaryTextFieldComponent
import com.boilerplate.app.presentation.components.TextComponent
import com.boilerplate.app.presentation.components.TextSmallTitleComponent
import com.boilerplate.app.presentation.components.VerticalSpacer
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.presentation.navigationcomponent.AuthNavRoute
import com.boilerplate.app.presentation.screens.auth.signup.SignupUIState
import com.boilerplate.app.presentation.screens.auth.viewmodel.AuthViewModel
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Dimens
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun ForgotPassScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val canShowSnackBar = remember { mutableStateOf(false) }
    val snackBarMessage = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(Resource.Failure(FailureStatus.NOTHING)) }
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        onDispose {
            coroutineScope.cancel()
        }
    }

    LaunchedEffect(key1 = Unit) {
        authViewModel.initializeState()
        coroutineScope.launch {
            authViewModel.signUpResponse.collect { signupState: SignupUIState ->
                isLoading = signupState.isLoading
                if (signupState.error != null && signupState.error.failureStatus != FailureStatus.NOTHING) {
                    error = signupState.error
                    canShowSnackBar.value = true
                    snackBarMessage.value = signupState.error.message.toString()
                } else {
                    canShowSnackBar.value = false
                    snackBarMessage.value = ""
                }
                signupState.data?.let {
                    navController.navigate(route = AuthNavRoute.Login.route, navOptions {
                        popUpTo(AuthNavRoute.Login.route) {
                            inclusive = true
                        }
                    })
                    Toast.makeText(context, authViewModel.getUser().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    AuthLayout(isLogin = null,
        isLoading = isLoading,
        canShowSnackBar = canShowSnackBar,
        snackBarMessage = snackBarMessage,
        onButtonClick = { navController.navigateUp() },
        content = {
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
                PrimaryTextFieldComponent(
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholderText = stringResource(R.string.email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    onTextChanged = {
                        authViewModel.onForgotPassEvent(ForgotPassUIEvent.EmailChanged(it))
                    },
                    errorStatus = authViewModel.forgotPassUIState.value.emailError,
                    errorMessage = authViewModel.forgotPassUIState.value.emailErrorMsg
                )

                VerticalSpacer()

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp), label = stringResource(R.string.send_recovery_link)
                ) {
                    authViewModel.updateSharedEmailData()
                    authViewModel.onForgotPassEvent(ForgotPassUIEvent.SendRecoverEmailButtonClicked)
                    navController.navigate(AuthNavRoute.OtpVerification.route)
                }

                VerticalSpacer()

                ClickableAuthTextComponent(
                    initialText = stringResource(R.string.remember_password),
                    clickAbleText = stringResource(R.string.login),
                    onTextSelected = {
                        navController.navigateUp()
                    })

            }
        })

}