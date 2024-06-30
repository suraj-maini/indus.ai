package com.boilerplate.app.presentation.navigationcomponent.navs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.boilerplate.app.presentation.navigationcomponent.HomeNavRoute
import com.boilerplate.app.presentation.screens.auth.viewmodel.AuthViewModel
import com.boilerplate.app.presentation.screens.home.HomeScreen

fun NavGraphBuilder.addHomeFeature(navController: NavController) {
    composable(HomeNavRoute.Home.route) {
        HomeScreen(navController)
    }
}