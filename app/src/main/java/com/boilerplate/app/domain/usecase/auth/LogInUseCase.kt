package com.boilerplate.app.domain.usecase.auth

import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.model.SignupResult
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.repository.auth.AuthRepository
import com.boilerplate.app.domain.utils.BaseResponse
import com.boilerplate.app.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LogInUseCase @Inject constructor(
  private val authRepository: AuthRepository
) {

  operator fun invoke(request: LogInRequest): Flow<Resource<BaseResponse<SignupResult>>> = flow {
    emit(Resource.Loading)

    val result = authRepository.logIn(request)
    /*if (result is Resource.Success) {
      saveUserToLocalUseCase(result.value.result)
    }*/

    emit(result)
  }.flowOn(Dispatchers.IO)
}