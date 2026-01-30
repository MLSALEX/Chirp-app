package com.alexmls.chat.domain.message

import com.alexmls.chat.domain.models.ChatMessage
import com.alexmls.chat.domain.models.ChatMessageDeliveryStatus
import com.alexmls.chat.domain.models.MessageWithSender
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow
import com.alexmls.core.domain.util.Result

interface MessageRepository {
    suspend fun updateMessageDeliveryStatus(
        messageId: String,
        status: ChatMessageDeliveryStatus
    ): EmptyResult<DataError.Local>

    suspend fun fetchMessages(
        chatId: String,
        before: String? = null
    ): Result<List<ChatMessage>, DataError>

    fun getMessagesForChat(chatId: String): Flow<List<MessageWithSender>>
}