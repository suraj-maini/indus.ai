package com.boilerplate.app.presentation.navigationcomponent

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.boilerplate.app.presentation.navigationcomponent.navs.addAuthFeature

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoute.Login.route) {
        addAuthFeature(navController)
        // Add other feature groups
    }
}
