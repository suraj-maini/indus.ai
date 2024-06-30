package com.boilerplate.app.presentation.navigationcomponent

sealed class AuthNavRoute(val route: String) {
    data object Login : AuthNavRoute("login")
    data object ForgotPassword : AuthNavRoute("forgotPassword")
    data object Signup : AuthNavRoute("signup"){
        val id = "id"
        val showDetails = "showDetails"
    }
    data object OtpVerification : AuthNavRoute("otpVerification")
    data object NewPassword : AuthNavRoute("newPassword")


    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}
