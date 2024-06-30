package com.boilerplate.app.presentation.navigationcomponent

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.boilerplate.app.presentation.navigationcomponent.navs.addAuthFeature
import com.boilerplate.app.presentation.screens.auth.viewmodel.AuthViewModel

@Composable
fun MainNavGraph(navController: NavHostController, authViewModel: AuthViewModel = hiltViewModel()) {
    NavHost(navController, startDestination = NavRoute.Login.route) {
        addAuthFeature(navController, authViewModel)
        // Add other feature groups
    }
}
