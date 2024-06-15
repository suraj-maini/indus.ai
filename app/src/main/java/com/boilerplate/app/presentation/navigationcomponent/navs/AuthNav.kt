package com.boilerplate.app.presentation.navigationcomponent.navs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.boilerplate.app.presentation.navigationcomponent.NavRoute
import com.boilerplate.app.presentation.screens.auth.ForgotPassScreen
import com.boilerplate.app.presentation.screens.auth.LoginScreen
import com.boilerplate.app.presentation.screens.auth.SignupScreen

fun NavGraphBuilder.addAuthFeature(navController: NavController) {
    composable(NavRoute.Login.route) {
        LoginScreen(navController)
    }
    composable(
        NavRoute.Signup.withArgsFormat(NavRoute.Signup.id, NavRoute.Signup.showDetails)
    ) { backStackEntry ->
        val args = backStackEntry.arguments

        // get id param value
        val id = args?.getInt("id")!!

        // get showDetails param value val
        val showDetails = args.getBoolean("showDetails")

        // call profile screen composable function here ...
        SignupScreen(navController, id, showDetails)
    }
    composable(NavRoute.ForgotPassword.route) {
        ForgotPassScreen(navController)
    }
}