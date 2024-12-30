package com.boilerplate.app.presentation.screens.auth.otpVerfificationScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.boilerplate.app.R
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.presentation.components.ClickableAuthTextComponent
import com.boilerplate.app.presentation.components.LoginTextComponent
import com.boilerplate.app.presentation.components.OtpTextField
import com.boilerplate.app.presentation.components.PrimaryButton
import com.boilerplate.app.presentation.components.TextComponent
import com.boilerplate.app.presentation.components.VerticalSpacer
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.presentation.navigationcomponent.AuthNavRoute
import com.boilerplate.app.presentation.screens.auth.viewmodel.AuthViewModel
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Dimens

@Composable
fun OtpVerificationScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val canShowSnackBar = remember { mutableStateOf(false) }
    val snackBarMessage = remember { mutableStateOf("") }
    val isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(Resource.Failure(FailureStatus.NOTHING)) }
    val coroutineScope = rememberCoroutineScope()

    var otpValue by rememberSaveable {
        mutableStateOf("")
    }

    val sharedEmail by authViewModel.sharedEmailData.collectAsState()

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
                    boldText = stringResource(R.string.otp),
                    italicText = stringResource(R.string.verification),
                    color = AppTheme.colorScheme.primary
                )

            TextComponent(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colorScheme.onBackgroundGray,
                fontSize = 16.sp,
                value = stringResource(R.string.please_enter_the_otp_sent_to).plus(sharedEmail)
            )

                VerticalSpacer(size = 30.dp)

                OtpTextField(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    otpText = otpValue,
                    onOtpTextChange = { value, otpInputFilled ->
                        otpValue = value
                        if (otpInputFilled) {
                            Toast.makeText(context, "OTP Verified", Toast.LENGTH_SHORT).show()
                        }
                    },
                    otpCount = 4
                )

                VerticalSpacer()

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp), label = stringResource(R.string.verify)
                ) {
                    navController.navigate(AuthNavRoute.NewPassword.route)
                }

                VerticalSpacer()

                ClickableAuthTextComponent(
                    initialText = stringResource(R.string.did_not_receive_the_code),
                    clickAbleText = stringResource(R.string.resend_code),
                    onTextSelected = {
                        navController.navigateUp()
                    })

            }
        })

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    AppTheme {
        OtpVerificationScreen(navController = NavController(LocalContext.current))
    }
}