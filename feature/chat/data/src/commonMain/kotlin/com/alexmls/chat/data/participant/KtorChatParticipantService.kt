package com.alexmls.chat.data.participant

import com.alexmls.chat.data.dto.ChatParticipantDto
import com.alexmls.chat.data.mappers.toDomain
import com.alexmls.chat.domain.participant.ChatParticipantService
import com.alexmls.chat.domain.models.ChatParticipant
import com.alexmls.core.data.networking.get
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.Result
import com.alexmls.core.domain.util.map
import io.ktor.client.HttpClient

class KtorChatParticipantService(
    private val httpClient: HttpClient
): ChatParticipantService {

    override suspend fun searchParticipant(query: String): Result<ChatParticipant, DataError.Remote> {
        return httpClient.get<ChatParticipantDto>(
            route = "/participants",
            queryParams = mapOf(
                "query" to query
            )
        ).map { it.toDomain() }
    }

    override suspend fun getLocalParticipant(): Result<ChatParticipant, DataError.Remote> {
        return httpClient.get<ChatParticipantDto>(
            route = "/participants"
        ).map { it.toDomain() }
    }
}