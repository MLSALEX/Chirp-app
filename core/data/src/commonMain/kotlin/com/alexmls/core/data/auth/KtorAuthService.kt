package com.alexmls.core.data.auth

import com.alexmls.core.data.dto.AuthInfoSerializable
import com.alexmls.core.data.dto.requests.ChangePasswordRequest
import com.alexmls.core.data.dto.requests.EmailRequest
import com.alexmls.core.data.dto.requests.LoginRequest
import com.alexmls.core.data.dto.requests.RegisterRequest
import com.alexmls.core.data.dto.requests.ResetPasswordRequest
import com.alexmls.core.data.mappers.toDomain
import com.alexmls.core.data.networking.get
import com.alexmls.core.data.networking.post
import com.alexmls.core.domain.auth.AuthInfo
import com.alexmls.core.domain.auth.AuthService
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.EmptyResult
import com.alexmls.core.domain.util.Result
import com.alexmls.core.domain.util.map
import io.ktor.client.HttpClient

class KtorAuthService(
    private val httpClient: HttpClient
): AuthService {

    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthInfo, DataError.Remote> {
        return httpClient.post<LoginRequest, AuthInfoSerializable>(
            route = "/auth/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        ).map { authInfoSerializable ->
            authInfoSerializable.toDomain()
        }
    }


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

    override suspend fun verifyEmail(token: String): EmptyResult<DataError.Remote> {
        return httpClient.get(
            route = "/auth/verify",
            queryParams = mapOf("token" to token)
        )
    }

    override suspend fun forgotPassword(email: String): EmptyResult<DataError.Remote> {
        return httpClient.post<EmailRequest, Unit>(
            route = "/auth/forgot-password",
            body = EmailRequest(email)
        )
    }

    override suspend fun resetPassword(
        newPassword: String,
        token: String
    ): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/reset-password",
            body = ResetPasswordRequest(
                newPassword = newPassword,
                token = token
            )
        )
    }

    override suspend fun changePassword(
        currentPassword: String,
        newPassword: String
    ): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/change-password",
            body = ChangePasswordRequest(
                oldPassword = currentPassword,
                newPassword = newPassword
            )
        )
    }
}