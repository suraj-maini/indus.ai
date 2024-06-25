package com.boilerplate.app.presentation.screens.auth

object Validator {

    fun validateFirstName(fName: String): ValidationResult {
        val isValid = fName.isNotEmpty() && fName.length >= 2 && fName.all { it.isLetter() }
        return ValidationResult(
            isValid,
            if (isValid) null else "First name should be at least 2 characters long and contain only letters."
        )
    }

    fun validateLastName(lName: String): ValidationResult {
        val isValid = lName.isNotEmpty() && lName.length >= 2 && lName.all { it.isLetter() }
        return ValidationResult(
            isValid,
            if (isValid) null else "Last name should be at least 2 characters long and contain only letters."
        )
    }

    fun validateEmail(email: String): ValidationResult {
        val emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
        val isValid = email.isNotEmpty() && email.matches(emailPattern)
        return ValidationResult(
            isValid,
            if (isValid) null else "Please enter a valid email, for example your_email@mail.com."
        )
    }

    fun validatePassword(password: String): ValidationResult {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex()
        val isValid = password.isNotEmpty() && password.matches(passwordPattern)
        return ValidationResult(
            isValid,
            if (isValid) null else "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character."
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue: Boolean): ValidationResult {
        return ValidationResult(
            statusValue,
            if (statusValue) null else "Privacy policy must be accepted."
        )
    }

}

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)