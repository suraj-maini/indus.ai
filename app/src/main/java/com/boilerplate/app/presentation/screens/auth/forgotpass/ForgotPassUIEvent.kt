package com.boilerplate.app.presentation.screens.auth.forgotpass

sealed class ForgotPassUIEvent{
    data class EmailChanged(val email:String): ForgotPassUIEvent()

    data object SendRecoverEmailButtonClicked : ForgotPassUIEvent()
}
