package com.boilerplate.app.data.service

import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.model.SignupResult
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/mob/login")
    suspend fun logIn(@Body request: LogInRequest): BaseResponse<SignupResult>
}