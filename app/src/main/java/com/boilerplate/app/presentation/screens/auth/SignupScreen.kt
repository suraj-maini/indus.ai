package com.boilerplate.app.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.boilerplate.app.R
import com.boilerplate.app.presentation.components.ClickableAuthTextComponent
import com.boilerplate.app.presentation.components.LoginTextComponent
import com.boilerplate.app.presentation.components.PasswordTextFieldComponent
import com.boilerplate.app.presentation.components.PrimaryButton
import com.boilerplate.app.presentation.components.PrimaryTextFieldComponent
import com.boilerplate.app.presentation.components.TextComponent
import com.boilerplate.app.presentation.components.TextSmallTitleComponent
import com.boilerplate.app.presentation.components.VerticalSpacer
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Dimens
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(
    navController: NavController,
    id: Int,
    showDetails: Boolean
) {

    AuthLayout(isLogin = false, onButtonClick = {
        navController.navigateUp()
    }) {

        val scrollState = rememberScrollState()
        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(Dimens.defaultScreenPadding)
                .background(AppTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            LoginTextComponent(
                modifier = Modifier.fillMaxWidth(),
                boldText = "Sign",
                italicText = "Up"
            )

            TextComponent(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colorScheme.onBackgroundGray,
                value = "Welcome to ".plus(stringResource(id = R.string.app_name))
            )
            VerticalSpacer(size = 30.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    16.dp,
                    Alignment.Start
                ) // Space between the text fields
            ) {
                TextSmallTitleComponent(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp),
                    value = "First Name"
                )

                TextSmallTitleComponent(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp),
                    value = "Last Name"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp) // Space between the text fields
            ) {
                PrimaryTextFieldComponent(
                    modifier = Modifier.weight(1f),
                    placeholderText = "First Name"
                )

                PrimaryTextFieldComponent(
                    modifier = Modifier.weight(1f),
                    placeholderText = "Last Name"
                )
            }

            VerticalSpacer()

            TextSmallTitleComponent(
                value = "Email",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PrimaryTextFieldComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                placeholderText = "Email"
            )

            TextSmallTitleComponent(
                value = "Password",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PasswordTextFieldComponent(
                placeholderText = "Password"
            )

            TextSmallTitleComponent(
                value = "Confirm Password",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            PasswordTextFieldComponent(
                placeholderText = "Confirm Password", keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            VerticalSpacer()

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                label = "Start creating with ".plus(stringResource(id = R.string.app_name))
            ) {

            }

            VerticalSpacer()

            ClickableAuthTextComponent(
                textSize = 12.sp,
                initialText = "If already have an account, please click to here",
                clickAbleText = "Login",
                onTextSelected = {
                    navController.navigateUp()
                })

        }

    }


    /*BackHandler(true) {}
    SimpleTopAppBar(navController, id, showDetails)*/
    /*Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                navigationIcon = {
                    IconButton(onClick = { *//* Handle navigation icon click *//* }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { *//* Handle FAB click *//* }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            BottomAppBar {
                Text("Bottom App Bar")
            }
        }
    ) { paddingValues ->
        // Main content goes here
        Text(
            text = "Hello, Scaffold!",
            modifier = Modifier.padding(paddingValues)
        )
    }*/
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(navController: NavController, id: Int, showDetails: Boolean) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val items = listOf(Icons.Default.Close, Icons.Default.Clear, Icons.Default.Call)
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(.8f)
            ) {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
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
                }
            }


        },
        content = {

            Scaffold(
                containerColor = AppTheme.colorScheme.background,
                topBar = {
                    /*TopAppBar(
                        title = {
                            Text(
                                "Simple TopAppBar ".plus(id.toString()).plus(" ").plus(showDetails),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        }
                    )*/
                    DynamicAppBar(
                        title = "Kenna AI",
                        navigationIcon = Icons.Default.Menu,
                        onNavigationIconClick = {
                            scope.launch { drawerState.open() }
                            /* Handle navigation icon click */
                        },
                        actions = {
                            IconButton(
                                onClick = { /* Handle action click */ },
                                colors = IconButtonDefaults.iconButtonColors(contentColor = AppTheme.colorScheme.onBackground)
                            ) {
                                Icon(Icons.Default.Search, contentDescription = null)
                            }
                            IconButton(
                                onClick = { /* Handle action click */ },
                                colors = IconButtonDefaults.iconButtonColors(contentColor = AppTheme.colorScheme.onBackground)
                            ) {
                                Icon(Icons.Default.Settings, contentDescription = null)
                            }
                        }
                    )
                },
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navController.navigateUp()
                        },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                }
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicAppBar(
    title: String,
    navigationIcon: ImageVector? = null,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = AppTheme.colorScheme.background),
        title = { TextComponent(value = title) },
        navigationIcon = {
            navigationIcon?.let {
                /*IconButton(
                    onClick = { onNavigationIconClick?.invoke() },
                    colors = IconButtonDefaults.iconButtonColors(contentColor = AppTheme.colorScheme.onBackground),
                    modifier = Modifier.width(100.dp)
                ) {
                    Icon(imageVector = it, contentDescription = null)
                }*/

                Surface(
                    modifier = Modifier
                        .width(100.dp)
                        .background(AppTheme.colorScheme.background)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.indus_logo),
                        contentDescription = null
                    )
                }
            }
        },
        actions = actions
    )
}