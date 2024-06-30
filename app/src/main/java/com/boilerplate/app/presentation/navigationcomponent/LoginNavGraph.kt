package com.boilerplate.app.presentation.navigationcomponent

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.boilerplate.app.presentation.navigationcomponent.navs.addAuthFeature
import com.boilerplate.app.presentation.screens.auth.viewmodel.AuthViewModel

@Composable
fun LoginNavGraph(authViewModel: AuthViewModel = hiltViewModel(), onLogin: () -> Unit) {
    /*val user = authViewModel.getUser.collectAsState()

    LaunchedEffect(user.value) {
        if (!user.value.email.isNullOrEmpty()) {
            navController.navigate(HomeNavRoute.Home.route) {
                popUpTo(HomeNavRoute.Home.route) {
                    inclusive = true
                }
            }
        }else{
            navController.navigate(AuthNavRoute.Login.route) {
                popUpTo(AuthNavRoute.Login.route) {
                    inclusive = true
                }
            }
        }
    }*/

    val navController = rememberNavController()

    NavHost(navController, startDestination = AuthNavRoute.Login.route) {
        addAuthFeature(navController, authViewModel, onLogin)
    }
}
