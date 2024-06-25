package com.boilerplate.app.presentation.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.fragment.app.Fragment
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun SnackbarWithoutScaffold(
    message: String,
    showRetryButton: Boolean = false,
    openSnackbar: (Boolean) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    SnackbarHost(
        modifier = Modifier,
        hostState = snackbarHostState
    ) {
        Snackbar(
            snackbarData = it,
            containerColor = AppTheme.colorScheme.onBackground,
            contentColor = AppTheme.colorScheme.background,
            actionColor = AppTheme.colorScheme.primary
        )
    }
    SideEffect {
        snackScope.launch {
            Log.d("Snackbaaaar", "LaunchedEffect: ")
            snackbarHostState.showSnackbar(
                message,
                actionLabel = if (showRetryButton) "Retry" else null,
                duration = SnackbarDuration.Short
            )
        }
        openSnackbar(false)

    }
}

/*@Composable
fun HandleApiError(
    failure: Resource.Failure,
    retryAction: (() -> Unit)? = null,
    noDataAction: (() -> Unit)? = null,
    noInternetAction: (() -> Unit)? = null,
    snackbarHostState: SnackbarHostState
) {
    val showDialog = remember { mutableStateOf(true) }
    when (failure.failureStatus) {
        FailureStatus.EMPTY -> {
            noDataAction?.invoke()
        }

        FailureStatus.NO_INTERNET -> {
            noInternetAction?.invoke()
            if (showDialog.value) {
                AlertDialogDemo(showDialog = showDialog)
            }
        }

        FailureStatus.API_FAIL, FailureStatus.OTHER -> {
            noDataAction?.invoke()

            SnackbarWithoutScaffold(
                snackbarHostState = snackbarHostState,
                message = failure.message!!,
                showSb = true
            ) {

            }

//            requireView().showSnackBar(
//                failure.message ?: resources.getString(R.string.some_error),
//                resources.getString(R.string.retry),
//                retryAction
//            )

        }
    }
}*/


@Composable
fun AlertDialogDemo(showDialog: MutableState<Boolean>) {
    // State to control the visibility of the dialog
//    val showDialog = remember { mutableStateOf(true) }

    // Main UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Show the AlertDialog if showDialog is true
        if (showDialog.value) {
            AlertDialog(
                containerColor = AppTheme.colorScheme.background,
                iconContentColor = AppTheme.colorScheme.onBackgroundGray,
                titleContentColor = AppTheme.colorScheme.onBackground,
                textContentColor = AppTheme.colorScheme.onBackground,
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                ),
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside or presses the back button
                    showDialog.value = false
                },
                title = {
                    Text(text = "Alert Dialog Title")
                },
                text = {
                    Text("This is an example of AlertDialog in Jetpack Compose.")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            // Handle confirm button click
                            showDialog.value = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            // Handle dismiss button click
                            showDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

