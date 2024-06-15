package com.boilerplate.app.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.boilerplate.app.presentation.components.DynamicAppBar
import com.boilerplate.app.presentation.components.SecondaryButton
import com.boilerplate.app.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthLayout(
    isLogin: Boolean?,
    onButtonClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
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
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content(innerPadding)
        }
    }
}
