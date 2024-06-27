package com.boilerplate.app.presentation.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.boilerplate.app.R
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.presentation.components.DynamicAppBar
import com.boilerplate.app.presentation.components.SecondaryButton
import com.boilerplate.app.theme.AppTheme
import com.boilerplate.app.theme.TransparentBlack
import com.boilerplate.app.utils.hideKeyboardOnOutsideClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthLayout(
    isLogin: Boolean?,
    isLoading: Boolean = false,
    canShowSnackBar: MutableState<Boolean> = mutableStateOf(false),
    snackBarMessage: MutableState<String> = mutableStateOf(""),
    onButtonClick1: Resource.Failure = Resource.Failure(FailureStatus.NOTHING),
    onButtonClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {

    var showRetryButton by remember {
        mutableStateOf(false)
    }

    HandleApiError(failure = onButtonClick1, noDataAction = {
        canShowSnackBar.value = true
        snackBarMessage.value = it
    },
        noInternetAction = {
            canShowSnackBar.value = true
            snackBarMessage.value = it
        })

    Box {
        Scaffold(
            containerColor = AppTheme.colorScheme.background,
            contentColor = AppTheme.colorScheme.onBackground,
            topBar = {
                DynamicAppBar(
                    actions = {
                        /*SecondaryButton(
                        label = "Login",
                        onClick = { *//* Handle action click *//* })
                    Spacer(modifier = Modifier.width(7.dp))*/
                        isLogin?.let {
                            SecondaryButton(
                                label = if (isLogin) "Signup" else "Login",
                                onClick = { onButtonClick.invoke() })
                        }
                    }
                )
            },
            snackbarHost = {
                if (canShowSnackBar.value) {
                    SnackbarWithoutScaffold(
                        message = snackBarMessage.value,
                        showRetryButton = showRetryButton,
                    ) {
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .hideKeyboardOnOutsideClick()
            ) {
                content(innerPadding)
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(TransparentBlack),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier,
                    color = AppTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun HandleApiError(
    failure: Resource.Failure,
    retryAction: (() -> Unit)? = null,
    noDataAction: ((String) -> Unit)? = null,
    noInternetAction: ((String) -> Unit)? = null
) {
    when (failure.failureStatus) {
        FailureStatus.EMPTY -> {
            noDataAction?.invoke(
                failure.message ?: ContextCompat.getString(
                    LocalContext.current,
                    R.string.list_no_result
                )
            )
        }

        FailureStatus.NO_INTERNET -> {
            noInternetAction?.invoke(
                failure.message ?: ContextCompat.getString(
                    LocalContext.current,
                    R.string.no_internet
                )
            )
        }

        FailureStatus.API_FAIL, FailureStatus.OTHER -> {
            noDataAction?.invoke(
                failure.message ?: ContextCompat.getString(
                    LocalContext.current,
                    R.string.some_error
                )
            )

            /*SnackbarWithoutScaffold(
                snackbarHostState = snackbarHostState,
                message = failure.message!!,
                showSb = true
            ) {

            }*/

//            requireView().showSnackBar(
//                failure.message ?: resources.getString(R.string.some_error),
//                resources.getString(R.string.retry),
//                retryAction
//            )

        }

        FailureStatus.NOTHING -> {}
    }
}

