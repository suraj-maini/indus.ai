package com.boilerplate.app.presentation.screens.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.boilerplate.app.base.BaseViewModel
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.usecase.auth.LogInUseCase
import com.boilerplate.app.data.BaseResponse
import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.model.SignUpResponse
import com.boilerplate.app.data.models.auth.request.SignUpRequest
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.usecase.auth.SignUpUseCase
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.domain.utils.SingleLiveEvent
import com.boilerplate.app.presentation.screens.auth.Validator
import com.boilerplate.app.utils.SharedPreferencesHelper
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
    private val signupUseCase: SignUpUseCase,
    private val sharedPrefs: SharedPreferencesHelper
) : BaseViewModel() {

    var loginUIState = mutableStateOf(LoginUIState())

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

    private val _logInResponse =
        MutableStateFlow(LoginUIState())
    val logInResponse = _logInResponse

    private fun login() {
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        val loginRequest = LogInRequest(email, password)

        logInUseCase(loginRequest)
            /*.catch { exception -> validationException.value = exception.message?.toInt() }*/
            .onEach { loginResponse ->

                when (loginResponse) {
                    Resource.Default -> {}
                    is Resource.Failure -> {
                        _logInResponse.value = LoginUIState(error = loginResponse, isLoading = false)
                    }
                    Resource.Loading -> {
                        _logInResponse.value = LoginUIState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _logInResponse.value = LoginUIState(data = loginResponse.value, isLoading = false)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun initializeState(){
        viewModelScope.launch {
            _logInResponse.emit(LoginUIState(error = Resource.Failure(FailureStatus.NOTHING)))
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
//        return emailResult.isValid && passwordResult.isValid
        return emailResult.isValid

    }

    fun getUser(): LoginResponse = sharedPrefs.getUser()


    var signUpRequest = SignUpRequest()

    var togglePassword = SingleLiveEvent<Void>()
    var openForgotPassword = SingleLiveEvent<Void>()

    var validationException = SingleLiveEvent<Int>()

    fun onPasswordToggleClicked() {
        togglePassword.call()
    }

    fun onForgotPasswordClicked() {
        openForgotPassword.call()
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