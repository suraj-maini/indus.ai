package com.boilerplate.app.presentation.navigationcomponent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.boilerplate.app.presentation.screens.home.MainApp
import com.boilerplate.app.presentation.screens.auth.viewmodel.AuthViewModel

@Composable
fun AppNavigation(authViewModel: AuthViewModel = hiltViewModel()) {
    var isLoggedIn by remember { mutableStateOf(authViewModel.getUser().isLoggedIn) }

    if (isLoggedIn) {
        MainApp(onLogout = {
            authViewModel.updateUser(authViewModel.getUser().copy(isLoggedIn = false))
            isLoggedIn = false
        })
    } else {
        LoginNavGraph(authViewModel, onLogin = {
            authViewModel.updateUser(authViewModel.getUser().copy(isLoggedIn = true))
            isLoggedIn = true
        })
    }
}

