package com.boilerplate.app.data.models.auth.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpRequest(
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "first_name")
    var firstName: String? = null,
    @Json(name = "last_name")
    var lastName: String? = null,
    @Json(name = "password")
    var password: String? = null,
    @Json(name = "password_confirmation")
    var passwordConfirmation: String? = null,
    @Json(name = "plan")
    var plan: String? = null
)