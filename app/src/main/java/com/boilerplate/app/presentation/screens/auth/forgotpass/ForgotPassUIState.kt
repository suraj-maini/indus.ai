package com.boilerplate.app.presentation.screens.auth.forgotpass

import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.data.models.auth.model.SignUpResponse
import com.boilerplate.app.domain.utils.Resource

data class ForgotPassUIState(

    var email  :String = "",
    var emailError :Boolean = false,
    var emailErrorMsg :String? = "",

    val isLoading: Boolean = false,
    val data: BaseResponse<SignUpResponse>? = null,
    val error: Resource.Failure? = null

)
