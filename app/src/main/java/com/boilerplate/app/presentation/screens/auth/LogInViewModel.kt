package com.boilerplate.app.presentation.screens.auth

import androidx.lifecycle.viewModelScope
import com.boilerplate.app.base.BaseViewModel
import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.usecase.auth.LogInUseCase
import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.data.models.auth.model.SignUpResponse
import com.boilerplate.app.data.models.auth.request.SignUpRequest
import com.boilerplate.app.domain.usecase.auth.SignUpUseCase
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.domain.utils.SingleLiveEvent
//import com.boilerplate.app.utils.EncryptedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val signupUseCase: SignUpUseCase
) : BaseViewModel() {

    var loginRequest = LogInRequest()
    var signUpRequest = SignUpRequest()
    private val _logInResponse =
        MutableStateFlow<Resource<BaseResponse<LoginResponse>>>(Resource.Default)
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
        logInUseCase(loginRequest)
            .catch { exception -> validationException.value = exception.message?.toInt() }
            .onEach { loginResponse ->

                _logInResponse.value = loginResponse
            }
            .launchIn(viewModelScope)
    }


    private val _signUpResponse =
        MutableStateFlow<Resource<BaseResponse<SignUpResponse>>>(Resource.Default)
    val signUpResponse = _signUpResponse

    fun onSignUpClicked() {
        signupUseCase(signUpRequest)
            .catch { exception -> validationException.value = exception.message?.toInt() }
            .onEach { signUpResponse ->

                _signUpResponse.value = signUpResponse
            }
            .launchIn(viewModelScope)
    }
}