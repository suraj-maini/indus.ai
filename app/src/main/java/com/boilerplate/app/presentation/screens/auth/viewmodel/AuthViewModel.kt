package com.boilerplate.app.presentation.screens.auth.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.boilerplate.app.base.BaseViewModel
import com.boilerplate.app.data.models.auth.request.LogInRequest
import com.boilerplate.app.domain.usecase.auth.LogInUseCase
import com.boilerplate.app.data.models.auth.model.LoginResponse
import com.boilerplate.app.data.models.auth.request.SignUpRequest
import com.boilerplate.app.domain.usecase.auth.SignUpUseCase
import com.boilerplate.app.domain.utils.FailureStatus
import com.boilerplate.app.domain.utils.Resource
import com.boilerplate.app.presentation.screens.auth.Validator
import com.boilerplate.app.presentation.screens.auth.login.LoginUIEvent
import com.boilerplate.app.presentation.screens.auth.login.LoginUIState
import com.boilerplate.app.presentation.screens.auth.signup.SignupUIEvent
import com.boilerplate.app.presentation.screens.auth.signup.SignupUIState
import com.boilerplate.app.utils.SharedPreferencesHelper
//import com.boilerplate.app.utils.EncryptedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val signupUseCase: SignUpUseCase,
    private val sharedPrefs: SharedPreferencesHelper
) : BaseViewModel() {

    var loginUIState = mutableStateOf(LoginUIState())
    var signupUIState = mutableStateOf(SignupUIState())

    fun initializeState() {
        viewModelScope.launch {
            _logInResponse.emit(LoginUIState(error = Resource.Failure(FailureStatus.NOTHING)))
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
                        _logInResponse.value =
                            LoginUIState(error = loginResponse, isLoading = false)
                    }

                    Resource.Loading -> {
                        _logInResponse.value = LoginUIState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _logInResponse.value =
                            LoginUIState(data = loginResponse.value, isLoading = false)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun getUser(): LoginResponse = sharedPrefs.getUser()

    private val _signUpResponse =
        MutableStateFlow(SignupUIState())
    val signUpResponse = _signUpResponse

    fun signUp() {

        val firstName = signupUIState.value.firstName
        val lastName = signupUIState.value.lastName
        val email = signupUIState.value.email
        val password = signupUIState.value.password
        val confirmPassword = signupUIState.value.confirmPassword
        val plan = "standard"

        val signUpRequest = SignUpRequest(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            passwordConfirmation = confirmPassword,
            plan = plan
        )

        signupUseCase(signUpRequest)
            .onEach { signUpResponse ->

                when (signUpResponse) {
                    Resource.Default -> {}
                    is Resource.Failure -> {
                        _signUpResponse.value =
                            SignupUIState(error = signUpResponse, isLoading = false)
                    }

                    Resource.Loading -> {
                        _signUpResponse.value = SignupUIState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _signUpResponse.value =
                            SignupUIState(data = signUpResponse.value, isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun onLoginEvent(event: LoginUIEvent) {
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

    fun onSignupEvent(event: SignupUIEvent) {
        when (event) {

            is SignupUIEvent.FirstNameChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    firstName = event.firstName
                )
            }

            is SignupUIEvent.LastNameChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    lastName = event.lastName
                )
            }

            is SignupUIEvent.EmailChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    email = event.email
                )
            }

            is SignupUIEvent.PasswordChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    password = event.password
                )
            }
            is SignupUIEvent.ConfirmPasswordChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    confirmPassword = event.confirmPassword
                )
            }

            SignupUIEvent.SignupButtonClicked -> {
                if (validateSignupUIDataWithRules()){
                    signUp()
                }
            }

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

    private fun validateSignupUIDataWithRules(): Boolean {
        val firstNameResult = Validator.validateFirstName(
            fName = signupUIState.value.firstName
        )

        val lastNameResult = Validator.validateLastName(
            lName = signupUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = signupUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = signupUIState.value.password
        )

        val confirmPasswordResult = Validator.validateConfirmPassword(
            password = signupUIState.value.password,
            confirmPassword = signupUIState.value.confirmPassword
        )

        signupUIState.value = signupUIState.value.copy(
            firstNameError = firstNameResult.isValid,
            firstNameErrorMsg = firstNameResult.errorMessage,
            lastNameError = lastNameResult.isValid,
            lastNameErrorMsg = lastNameResult.errorMessage,
            emailError = emailResult.isValid,
            emailErrorMsg = emailResult.errorMessage,
            passwordError = passwordResult.isValid,
            passwordErrorMsg = passwordResult.errorMessage,
            confirmPasswordError = confirmPasswordResult.isValid,
            confirmPasswordErrorMsg = confirmPasswordResult.errorMessage
        )

//        allValidationsPassed.value = emailResult.isValid && passwordResult.isValid
        return emailResult.isValid && passwordResult.isValid && confirmPasswordResult.isValid && firstNameResult.isValid && lastNameResult.isValid
//        return emailResult.isValid

    }

}