package com.alexmls.auth.presentation.di

import com.alexmls.auth.presentation.email_verification.EmailVerificationViewModel
import com.alexmls.auth.presentation.register.RegisterViewModel
import com.alexmls.auth.presentation.register_success.RegisterSuccessViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RegisterSuccessViewModel)
    viewModelOf(::EmailVerificationViewModel)
}