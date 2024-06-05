package com.boilerplate.app.data.repository.auth

import com.boilerplate.app.data.datasource.auth.AuthRemoteDataSource
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

  override
  suspend fun logIn(request: LogInRequest) = remoteDataSource.logIn(request)
}