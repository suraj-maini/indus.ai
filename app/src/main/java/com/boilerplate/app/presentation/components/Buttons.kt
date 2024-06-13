package com.boilerplate.app.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boilerplate.app.theme.AppTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colorScheme.primary,
            contentColor = AppTheme.colorScheme.onPrimary
        ),
        shape = AppTheme.shape.button
    ) {
        Text(
            text = label,
            style = AppTheme.typography.labelNormal
        )
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = AppTheme.colorScheme.background,
            contentColor = AppTheme.colorScheme.primary
        ),
        shape = AppTheme.shape.button,
        border = BorderStroke(2.dp, AppTheme.colorScheme.primary)
    ) {
        Text(
            text = label,
            style = AppTheme.typography.labelNormal
        )
    }
}

@Composable
fun RedBGSecondaryButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = AppTheme.colorScheme.workButtonBackground,
            contentColor = AppTheme.colorScheme.primary
        ),
        shape = AppTheme.shape.button,
        border = BorderStroke(2.dp, AppTheme.colorScheme.primary)
    ) {
        Text(
            text = label,
            style = AppTheme.typography.labelNormal
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewPrimaryButton() {

    /*PrimaryButton(label = "Primary"){}*/
    SecondaryButton(label = "Secondary") {
    }

    /*AppTheme {
        Column(
            modifier = Modifier
                .padding(AppTheme.size.medium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
        ) {
            PrimaryButton(
                label = "Primary",
                onClick = {}
            )
            SecondaryButton(
                label = "Secondary",
                onClick = {}
            )
        }
    }*/
}
