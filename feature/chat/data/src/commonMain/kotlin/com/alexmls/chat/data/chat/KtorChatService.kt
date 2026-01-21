package com.alexmls.chat.data.chat

import com.alexmls.chat.data.dto.ChatDto
import com.alexmls.chat.data.dto.request.CreateChatRequest
import com.alexmls.chat.data.mappers.toDomain
import com.alexmls.chat.domain.chat.ChatService
import com.alexmls.chat.domain.models.Chat
import com.alexmls.core.data.networking.post
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.Result
import io.ktor.client.HttpClient
import com.alexmls.core.domain.util.map

class KtorChatService(
    private val httpClient: HttpClient
): ChatService {

    override suspend fun createChat(otherUserIds: List<String>): Result<Chat, DataError.Remote> {
        return httpClient.post<CreateChatRequest, ChatDto>(
            route = "/chat",
            body = CreateChatRequest(
                otherUserIds = otherUserIds
            )
        ).map { it.toDomain() }
    }
}