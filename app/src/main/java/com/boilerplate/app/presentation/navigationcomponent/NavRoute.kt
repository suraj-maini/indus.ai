package com.boilerplate.app.presentation.navigationcomponent

sealed class NavRoute(val route: String) {
    data object Login : NavRoute("home")
    data object Signup : NavRoute("signup"){
        val id = "id"
        val showDetails = "showDetails"
    }


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
