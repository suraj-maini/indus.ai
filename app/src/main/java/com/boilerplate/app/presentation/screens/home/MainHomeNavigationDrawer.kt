package com.boilerplate.app.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.presentation.navigationcomponent.MainNavGraph
import com.boilerplate.app.theme.AppTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp(onLogout: () -> Unit) {
    val navController = rememberNavController()


    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val items = listOf(Icons.Default.Close, Icons.Default.Clear, Icons.Default.Call)
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet {
                DrawerHeader()
                DrawerBody(navController = navController, selectedItem, drawerState){
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
            AuthLayout(onButtonClick = {}) {
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
fun DrawerBody(
    navController: NavHostController,
    selectedItem: MutableState<ImageVector>,
    drawerState: DrawerState,
    onLogout: () -> Unit
) {
    Column {
        DrawerItem("Screen One", selectedItem = selectedItem, drawerState = drawerState) {
            navController.navigate("screen_one") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }
        DrawerItem("Screen Two", selectedItem = selectedItem, drawerState = drawerState) {
            navController.navigate("screen_two") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }
        DrawerItem("Screen Three", selectedItem = selectedItem, drawerState = drawerState) {
            navController.navigate("screen_three") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }
        DrawerItem("Logout", selectedItem = selectedItem, drawerState = drawerState) {
            onLogout()
        }
    }
}

@Composable
fun DrawerItem(text: String, icon: ImageVector = Icons.Default.Close, selectedItem: MutableState<ImageVector>,
               drawerState: DrawerState, onClick: () -> Unit) {

    val scope = rememberCoroutineScope()

    NavigationDrawerItem(
        icon = { Icon(icon, contentDescription = null) },
        label = { Text(text) },
        onClick = {
            scope.launch { drawerState.close() }
//            selectedItem.value = item
            onClick()
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        selected = false
    )
}