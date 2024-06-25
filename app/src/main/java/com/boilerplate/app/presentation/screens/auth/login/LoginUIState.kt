package com.boilerplate.app.presentation.screens.auth.login

import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.domain.utils.Resource

data class LoginUIState(
    var email  :String = "",
    var password  :String = "",

    var emailError :Boolean = false,
    var passwordError : Boolean = false,

    var emailErrorMsg :String? = "",
    var passwordErrorMsg : String? = "",


    val isLoading: Boolean = false,
    val data: BaseResponse<LoginResponse>? = null,
    val error: Resource.Failure? = null

)
