package com.boilerplate.app.data.models.auth.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    @Json(name = "auth_token")
    val authToken: String,
    @Json(name = "refresh_token")
    val refreshToken: String
)