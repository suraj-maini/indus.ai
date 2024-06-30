package com.boilerplate.app.presentation.navigationcomponent

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.boilerplate.app.R
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.presentation.screens.auth.viewmodel.AuthViewModel
import com.boilerplate.app.theme.AppTheme
import kotlinx.coroutines.launch

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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(onLogout: () -> Unit) {
    val navController = rememberNavController()

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val items = listOf(Icons.Default.Close, Icons.Default.Clear, Icons.Default.Call)
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet {
                DrawerHeader()
                DrawerBody(navController = navController) {
                    onLogout()
                }
                /*items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }*/
            }

        },
        content = {
            AuthLayout(isLogin = false, onButtonClick = {}) {
                MainNavGraph(navController, onLogout = onLogout)
            }
        })
}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Menu", style = AppTheme.typography.labelLarge)
    }
}

@Composable
fun DrawerBody(navController: NavHostController, onLogout: () -> Unit) {
    Column {
        DrawerItem("Screen One") {
            navController.navigate("screen_one") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }
        DrawerItem("Screen Two") {
            navController.navigate("screen_two") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }
        DrawerItem("Screen Three") {
            navController.navigate("screen_three") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }
        DrawerItem("Logout") {
            onLogout()
        }
    }
}

@Composable
fun DrawerItem(text: String, icon: ImageVector = Icons.Default.Close, onClick: () -> Unit) {
    NavigationDrawerItem(
        icon = { Icon(icon, contentDescription = null) },
        label = { Text(text) },
        onClick = onClick,
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        selected = false
    )
}