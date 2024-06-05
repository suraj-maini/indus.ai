package com.boilerplate.app.data.models.auth.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LogInRequest(
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "password")
    var password: String? = null
)