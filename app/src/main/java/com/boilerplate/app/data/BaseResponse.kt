package com.boilerplate.app.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
  @Json(name = "data")
  val result: T,
  @Json(name = "message")
  val message: String,
  @Json(name = "code")
  val code: Int // 0 -> Success, 1 -> Failure
)