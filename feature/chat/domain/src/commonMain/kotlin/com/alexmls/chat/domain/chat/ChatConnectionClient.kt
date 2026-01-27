package com.alexmls.chat.domain.chat


import com.alexmls.chat.domain.error.ConnectionError
import com.alexmls.chat.domain.models.ChatMessage
import com.alexmls.chat.domain.models.ConnectionState
import com.alexmls.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ChatConnectionClient {
    val chatMessages: Flow<ChatMessage>
    val connectionState: StateFlow<ConnectionState>
    suspend fun sendChatMessage(message: ChatMessage): EmptyResult<ConnectionError>
}