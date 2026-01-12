package com.alexmls.core.data.auth

import com.alexmls.core.data.dto.requests.EmailRequest
import com.alexmls.core.data.dto.requests.RegisterRequest
import com.alexmls.core.data.networking.post
import com.alexmls.core.domain.auth.AuthService
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class KtorAuthService(
    private val httpClient: HttpClient
): AuthService {

    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/register",
            body = RegisterRequest(
                email = email,
                username = username,
                password = password
            )
        )
    }

    override suspend fun resendVerificationEmail(email: String): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/resend-verification",
            body = EmailRequest(email),
        )
    }
}