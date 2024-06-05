package com.boilerplate.app.domain.repository.auth

import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.model.SignupResult
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.utils.BaseResponse
import com.boilerplate.app.domain.utils.Resource

interface AuthRepository {

  suspend fun logIn(request: LogInRequest): Resource<BaseResponse<SignupResult>>
}