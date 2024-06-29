package com.boilerplate.app.presentation.screens.auth.signup

import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.boilerplate.app.R
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.presentation.components.ClickableAuthTextComponent
import com.boilerplate.app.presentation.components.LoginTextComponent
import com.boilerplate.app.presentation.components.PasswordTextFieldComponent
import com.boilerplate.app.presentation.components.PrimaryButton
import com.boilerplate.app.presentation.components.PrimaryTextFieldComponent
import com.boilerplate.app.presentation.components.TextComponent
import com.boilerplate.app.presentation.components.TextSmallTitleComponent
import com.boilerplate.app.presentation.components.VerticalSpacer
import com.boilerplate.app.presentation.composables.AuthLayout
import com.boilerplate.app.presentation.navigationcomponent.NavRoute
import com.boilerplate.app.presentation.screens.auth.viewmodel.AuthViewModel
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.Dimens
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    var canShowSnackBar = remember { mutableStateOf(false) }
    var snackBarMessage = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(Resource.Failure(FailureStatus.NOTHING)) }
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        onDispose {
            coroutineScope.cancel()
        }
    }

    LaunchedEffect(key1 = Unit) {
        authViewModel.initializeState()
        coroutineScope.launch {
            authViewModel.signUpResponse.collect { signupState: SignupUIState ->
                isLoading = signupState.isLoading
                if (signupState.error != null && signupState.error.failureStatus != FailureStatus.NOTHING) {
                    error = signupState.error
                    canShowSnackBar.value = true
                    snackBarMessage.value = signupState.error.message.toString()
                } else {
                    canShowSnackBar.value = false
                    snackBarMessage.value = ""
                }
                signupState.data?.let {
                    navController.navigate(route = NavRoute.Login.route, navOptions {
                        popUpTo(NavRoute.Login.route) {
                            inclusive = true
                        }
                    })
                    Toast.makeText(context, authViewModel.getUser().toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    AuthLayout(
        isLogin = false,
        isLoading = isLoading,
        canShowSnackBar = canShowSnackBar,
        snackBarMessage = snackBarMessage,
        onButtonClick1 = error, onButtonClick = {
            navController.navigateUp()
        }, content = {

            val scrollState = rememberScrollState()

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
                    fontSize = 16.sp,
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
                        placeholderText = stringResource(R.string.first_name),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onTextChanged = {
                            authViewModel.onSignupEvent(SignupUIEvent.FirstNameChanged(it))
                        },
                        errorStatus = authViewModel.signupUIState.value.firstNameError,
                        errorMessage = authViewModel.signupUIState.value.firstNameErrorMsg
                    )

                    PrimaryTextFieldComponent(
                        modifier = Modifier.weight(1f),
                        placeholderText = stringResource(R.string.last_name),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onTextChanged = {
                            authViewModel.onSignupEvent(SignupUIEvent.LastNameChanged(it))
                        },
                        errorStatus = authViewModel.signupUIState.value.lastNameError,
                        errorMessage = authViewModel.signupUIState.value.lastNameErrorMsg
                    )
                }

                VerticalSpacer()

                TextSmallTitleComponent(
                    value = stringResource(R.string.email),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                PrimaryTextFieldComponent(
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholderText = stringResource(R.string.email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    onTextChanged = {
                        authViewModel.onSignupEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = authViewModel.signupUIState.value.emailError,
                    errorMessage = authViewModel.signupUIState.value.emailErrorMsg
                )

                TextSmallTitleComponent(
                    value = stringResource(R.string.password),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                PasswordTextFieldComponent(
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholderText = stringResource(R.string.password),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    onTextChanged = {
                        authViewModel.onSignupEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = authViewModel.signupUIState.value.passwordError,
                    errorMessage = authViewModel.signupUIState.value.passwordErrorMsg
                )

                TextSmallTitleComponent(
                    value = "Confirm Password",
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                PasswordTextFieldComponent(
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholderText = stringResource(R.string.confirm_password),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    onTextChanged = {
                        authViewModel.onSignupEvent(SignupUIEvent.ConfirmPasswordChanged(it))
                    },
                    errorStatus = authViewModel.signupUIState.value.confirmPasswordError,
                    errorMessage = authViewModel.signupUIState.value.confirmPasswordErrorMsg
                )

                VerticalSpacer()

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    label = "Start creating with ".plus(stringResource(id = R.string.app_name))
                ) {
                    authViewModel.onSignupEvent(SignupUIEvent.SignupButtonClicked)
                }

                VerticalSpacer()

                ClickableAuthTextComponent(
                    textSize = 12.sp,
                    initialText = stringResource(R.string.if_already_have_an_account_please_click_to_here),
                    clickAbleText = stringResource(R.string.login),
                    onTextSelected = {
                        navController.navigateUp()
                    })

            }

        })
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