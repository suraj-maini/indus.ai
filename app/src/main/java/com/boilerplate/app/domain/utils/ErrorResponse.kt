package com.boilerplate.app.domain.utils

data class ErrorResponse(
  /*val detail: String,
  val instance: Any,
  val status: Int,
  val title: String,
  val type: String*/
  val code: Int,
  val message: String,
  val data: Any

)