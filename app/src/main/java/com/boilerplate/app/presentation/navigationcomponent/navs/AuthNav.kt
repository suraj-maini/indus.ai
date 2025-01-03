package com.boilerplate.app.presentation.navigationcomponent.navs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.boilerplate.app.presentation.navigationcomponent.AuthNavRoute
import com.boilerplate.app.presentation.screens.auth.forgotpass.ForgotPassScreen
import com.boilerplate.app.presentation.screens.auth.login.LoginScreen
import com.boilerplate.app.presentation.screens.auth.newPass.NewPassScreen
import com.boilerplate.app.presentation.screens.auth.otpVerfificationScreen.OtpVerificationScreen
import com.boilerplate.app.presentation.screens.auth.signup.SignupScreen
import com.boilerplate.app.presentation.screens.auth.viewmodel.AuthViewModel

fun NavGraphBuilder.addAuthFeature(
    navController: NavController,
    authViewModel: AuthViewModel,
    onLogin: () -> Unit
) {
    composable(AuthNavRoute.Login.route) {
        LoginScreen(navController, authViewModel, onLogin)
    }
    composable(
        AuthNavRoute.Signup.withArgsFormat(AuthNavRoute.Signup.id, AuthNavRoute.Signup.showDetails)
    ) { backStackEntry ->
        /*val args = backStackEntry.arguments

        // get id param value
        val id = args?.getInt("id")!!

        // get showDetails param value val
        val showDetails = args.getBoolean("showDetails")*/

        // call profile screen composable function here ...
        SignupScreen(navController, onLogin = onLogin)
    }
    composable(AuthNavRoute.ForgotPassword.route) {
        ForgotPassScreen(navController, authViewModel)
    }
    composable(AuthNavRoute.OtpVerification.route) {
        OtpVerificationScreen(navController, authViewModel)
    }
    composable(AuthNavRoute.NewPassword.route) {
        NewPassScreen(navController, authViewModel)
    }
}