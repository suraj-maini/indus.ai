package com.boilerplate.app.data.service

import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.model.TokenResponse
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.data.models.auth.request.RefreshTokenRequest
import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.data.models.auth.model.SignUpResponse
import com.boilerplate.app.data.models.auth.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/mob/login")
    suspend fun logIn(@Body request: LogInRequest): BaseResponse<LoginResponse>

    @POST("api/mob/register")
    suspend fun signup(@Body request: SignUpRequest): BaseResponse<SignUpResponse>

    @POST("auth/refresh")
    suspend fun refreshAuthToken(@Body request: RefreshTokenRequest): TokenResponse
}