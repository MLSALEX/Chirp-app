package com.alexmls.chat.data.mappers

import com.alexmls.chat.data.dto.response.ProfilePictureUploadUrlsResponse
import com.alexmls.chat.domain.models.ProfilePictureUploadUrls

fun ProfilePictureUploadUrlsResponse.toDomain(): ProfilePictureUploadUrls {
    return ProfilePictureUploadUrls(
        uploadUrl = uploadUrl,
        publicUrl = publicUrl,
        headers = headers
    )
}