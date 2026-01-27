package com.alexmls.chat.data.chat

import com.alexmls.chat.data.dto.websocket.WebSocketMessageDto
import com.alexmls.chat.data.mappers.toNewMessage
import com.alexmls.chat.data.network.KtorWebSocketConnector
import com.alexmls.chat.database.ChirpChatDatabase
import com.alexmls.chat.domain.chat.ChatConnectionClient
import com.alexmls.chat.domain.chat.ChatRepository
import com.alexmls.chat.domain.error.ConnectionError
import com.alexmls.chat.domain.message.MessageRepository
import com.alexmls.chat.domain.models.ChatMessage
import com.alexmls.chat.domain.models.ChatMessageDeliveryStatus
import com.alexmls.core.domain.auth.SessionStorage
import com.alexmls.core.domain.util.EmptyResult
import com.alexmls.core.domain.util.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

class WebSocketChatConnectionClient(
    private val webSocketConnector: KtorWebSocketConnector,
    private val chatRepository: ChatRepository,
    private val database: ChirpChatDatabase,
    private val sessionStorage: SessionStorage,
    private val json: Json,
    private val messageRepository: MessageRepository
): ChatConnectionClient {

    override val chatMessages: Flow<ChatMessage>
        get() = TODO("Not yet implemented")

    override val connectionState = webSocketConnector.connectionState

    override suspend fun sendChatMessage(message: ChatMessage): EmptyResult<ConnectionError> {
        val outgoingDto = message.toNewMessage()
        val webSocketMessage = WebSocketMessageDto(
            type = outgoingDto.type.name,
            payload = json.encodeToString(outgoingDto)
        )
        val rawJsonPayload = json.encodeToString(webSocketMessage)

        return webSocketConnector
            .sendMessage(rawJsonPayload)
            .onFailure { error ->
                messageRepository.updateMessageDeliveryStatus(
                    messageId = message.id,
                    status = ChatMessageDeliveryStatus.FAILED
                )
            }
    }
}