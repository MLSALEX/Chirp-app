package com.alexmls.auth.presentation.forgot_password

sealed interface ForgotPasswordAction {
    data object OnSubmitClick: ForgotPasswordAction
}