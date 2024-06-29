package com.boilerplate.app.presentation.screens.auth.signup

import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.model.SignUpResponse
import com.boilerplate.app.domain.utils.Resource

data class SignupUIState(
    var firstName  :String = "",
    var lastName  :String = "",
    var email  :String = "",
    var password  :String = "",
    var confirmPassword  :String = "",

    var emailError :Boolean = false,
    var passwordError : Boolean = false,
    var confirmPasswordError : Boolean = false,
    var firstNameError : Boolean = false,
    var lastNameError : Boolean = false,

    var emailErrorMsg :String? = "",
    var passwordErrorMsg : String? = "",
    var confirmPasswordErrorMsg : String? = "",
    var firstNameErrorMsg : String? = "",
    var lastNameErrorMsg : String? = "",


    val isLoading: Boolean = false,
    val data: BaseResponse<SignUpResponse>? = null,
    val error: Resource.Failure? = null

)
