package com.boilerplate.app.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.boilerplate.app.presentation.components.DynamicAppBar
import com.boilerplate.app.presentation.components.RedBGSecondaryButton
import com.boilerplate.app.presentation.components.SecondaryButton
import com.boilerplate.app.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseLayout(
    navigationIcon: ImageVector? = null,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    appBarContent: @Composable (TopAppBarScrollBehavior) -> Unit = { scrollBehavior ->

        DynamicAppBar(scrollBehavior){
            RedBGSecondaryButton(label = "Work") {

            }
        }
    },
    content: @Composable (PaddingValues) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        containerColor = AppTheme.colorScheme.background,
        contentColor = AppTheme.colorScheme.onBackground,
        topBar = {
            appBarContent(scrollBehavior)
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            content(innerPadding)
        }
    }
}