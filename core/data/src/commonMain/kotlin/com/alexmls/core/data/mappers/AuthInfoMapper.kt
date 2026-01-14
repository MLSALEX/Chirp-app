package com.alexmls.core.data.mappers

import com.alexmls.core.data.dto.AuthInfoSerializable
import com.alexmls.core.data.dto.UserSerializable
import com.alexmls.core.domain.auth.AuthInfo
import com.alexmls.core.domain.auth.User

fun AuthInfoSerializable.toDomain(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        user = user.toDomain()
    )
}

fun UserSerializable.toDomain(): User {
    return User(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasVerifiedEmail,
        profilePictureUrl = profilePictureUrl
    )
}
