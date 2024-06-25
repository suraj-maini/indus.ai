package com.boilerplate.app.presentation.screens.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.boilerplate.app.base.BaseViewModel
import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.usecase.auth.LogInUseCase
import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.data.models.auth.model.SignUpResponse
import com.boilerplate.app.data.models.auth.request.SignUpRequest
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.usecase.auth.SignUpUseCase
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.domain.utils.SingleLiveEvent
import com.boilerplate.app.presentation.screens.auth.Validator
//import com.boilerplate.app.utils.EncryptedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val signupUseCase: SignUpUseCase
) : BaseViewModel() {


    private val TAG = LogInViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)


    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                if (validateLoginUIDataWithRules()) {
                    login()
                }
            }
        }
    }

    private fun login() {
        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        loginRequest = LogInRequest(email, password)

        logInUseCase(loginRequest)
            .catch { exception -> validationException.value = exception.message?.toInt() }
            .onEach { loginResponse ->

                when (loginResponse) {
                    Resource.Default -> {}
                    is Resource.Failure -> {
                        _logInnResponse.value = LoginUIState(error = loginResponse, isLoading = false)
                    }
                    Resource.Loading -> {
                        _logInnResponse.value = LoginUIState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _logInnResponse.value = LoginUIState(data = loginResponse.value, isLoading = false)
                    }
                }

                loginInProgress.value = false
                _logInResponse.value = loginResponse
            }
            .launchIn(viewModelScope)
    }

    fun initializeState(){
        viewModelScope.launch {
            _logInnResponse.emit(LoginUIState(error = Resource.Failure(FailureStatus.NOTHING)))
        }
    }


    private fun validateLoginUIDataWithRules(): Boolean {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.isValid,
            emailErrorMsg = emailResult.errorMessage,
            passwordError = passwordResult.isValid,
            passwordErrorMsg = passwordResult.errorMessage
        )

//        allValidationsPassed.value = emailResult.isValid && passwordResult.isValid
        return emailResult.isValid && passwordResult.isValid

    }


    var loginRequest = LogInRequest()
    var signUpRequest = SignUpRequest()
    private val _logInResponse =
        MutableStateFlow<Resource<BaseResponse<LoginResponse>>>(Resource.Default)
    val logInResponse = _logInResponse

    private val _logInnResponse =
        MutableStateFlow(LoginUIState())
    val logInnResponse = _logInnResponse

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