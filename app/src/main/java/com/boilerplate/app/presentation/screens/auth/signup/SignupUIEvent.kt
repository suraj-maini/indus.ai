package com.boilerplate.app.presentation.screens.auth.signup

sealed class SignupUIEvent{

    data class FirstNameChanged(val firstName:String): SignupUIEvent()
    data class LastNameChanged(val lastName:String): SignupUIEvent()
    data class EmailChanged(val email:String): SignupUIEvent()
    data class PasswordChanged(val password: String) : SignupUIEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignupUIEvent()

    data object SignupButtonClicked : SignupUIEvent()
}
