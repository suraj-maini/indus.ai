package com.boilerplate.app.data.remote

import com.boilerplate.app.domain.utils.BaseResponse
import com.boilerplate.app.domain.utils.ErrorResponse
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.utils.Resource
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseRemoteDataSource @Inject constructor() {

  suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    try {
      val apiResponse = apiCall.invoke()

      when ((apiResponse as BaseResponse<*>).result) {
        null -> {
          return Resource.Failure(FailureStatus.EMPTY)
        }
        is List<*> -> {
          return if ((apiResponse.result as List<*>).isNotEmpty()) {
            Resource.Success(apiResponse)
          } else {
            Resource.Failure(FailureStatus.EMPTY)
          }
        }
        is Boolean -> {
          return if (apiResponse.result as Boolean) {
            Resource.Success(apiResponse)
          } else {
            Resource.Failure(FailureStatus.API_FAIL, 200, apiResponse.detail)
          }
        }
        else -> {
          return Resource.Success(apiResponse)
        }
      }
    } catch (throwable: Throwable) {
      when (throwable) {
        is HttpException -> {
          when {
            throwable.code() == 422 -> {
              val jObjError = JSONObject(throwable.response()!!.errorBody()!!.string())
              val apiResponse = jObjError.toString()

              val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
              val jsonAdapter = moshi.adapter(BaseResponse::class.java)
              val response = jsonAdapter.fromJson(apiResponse)

              return Resource.Failure(FailureStatus.API_FAIL, throwable.code(), response?.detail)
            }
            throwable.code() == 401 -> {

              val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
              val jsonAdapter = moshi.adapter(ErrorResponse::class.java)
              val errorResponse = jsonAdapter.fromJson(throwable.response()?.errorBody()!!.charStream().readText())

              return Resource.Failure(FailureStatus.API_FAIL, throwable.code(), errorResponse?.detail)
            }
            else -> {
              return if (throwable.response()?.errorBody()!!.charStream().readText().isEmpty()) {
                Resource.Failure(FailureStatus.API_FAIL, throwable.code())
              } else {
                try {
                  val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                  val jsonAdapter = moshi.adapter(ErrorResponse::class.java)
                  val errorResponse = jsonAdapter.fromJson(throwable.response()?.errorBody()!!.charStream().readText())

                  Resource.Failure(FailureStatus.API_FAIL, throwable.code(), errorResponse?.detail)
                } catch (e: JsonDataException) {
                  // Handle JSON data exceptions
                  Resource.Failure(FailureStatus.API_FAIL, throwable.code(), "Invalid JSON data")
                } catch (e: JsonEncodingException) {
                  // Handle JSON encoding exceptions
                  Resource.Failure(FailureStatus.API_FAIL, throwable.code(), "JSON encoding error")
                } catch (e: Exception) {
                  // Handle any other exceptions
                  Resource.Failure(FailureStatus.API_FAIL, throwable.code(), "Unknown error occurred")
                }
              }
            }
          }
        }

        is UnknownHostException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        is ConnectException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        else -> {
          return Resource.Failure(FailureStatus.OTHER)
        }
      }
    }
  }
}