package com.boilerplate.app.data.models.auth.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "code")
    var code: Int? = null,
    @Json(name = "data")
    var data: SignupResult? = null,
    @Json(name = "message")
    var message: String? = null
)

@JsonClass(generateAdapter = true)
data class SignupResult(
    @Json(name = "avatar_url")
    var avatarUrl: Any? = null,
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "first_name")
    var firstName: String? = null,
    @Json(name = "id")
    var id: Any? = null,
    @Json(name = "is_allow")
    var isAllow: Boolean? = null,
    @Json(name = "is_blocked")
    var isBlocked: Int? = null,
    @Json(name = "join")
    var join: String? = null,
    @Json(name = "last_name")
    var lastName: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "plan")
    var plan: String? = null,
    @Json(name = "token")
    var token: String? = null
)