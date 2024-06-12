package com.boilerplate.app.presentation.navigationcomponent

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainApp() {
    val navController = rememberNavController()
    MainNavGraph(navController = navController)
}