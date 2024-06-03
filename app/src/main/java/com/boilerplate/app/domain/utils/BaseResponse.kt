package com.boilerplate.app.domain.utils

data class BaseResponse<T>(
  val result: T,
  val detail: String
)