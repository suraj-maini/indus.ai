package com.boilerplate.app.presentation.auth

import androidx.lifecycle.viewModelScope
import com.boilerplate.app.base.BaseViewModel
import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.model.SignupResult
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.usecase.auth.LogInUseCase
import com.boilerplate.app.domain.utils.BaseResponse
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.domain.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
  private val logInUseCase: LogInUseCase
) : BaseViewModel() {

  var request = LogInRequest()
  private val _logInResponse = MutableStateFlow<Resource<BaseResponse<SignupResult>>>(Resource.Default)
  val logInResponse = _logInResponse

  var togglePassword = SingleLiveEvent<Void>()
  var openForgotPassword = SingleLiveEvent<Void>()

  var validationException = SingleLiveEvent<Int>()

  fun onPasswordToggleClicked() {
    togglePassword.call()
  }

  fun onForgotPasswordClicked() {
    openForgotPassword.call()
  }

  fun onLogInClicked() {
    logInUseCase(request)
      .catch { exception -> validationException.value = exception.message?.toInt() }
      .onEach { result ->
        _logInResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}