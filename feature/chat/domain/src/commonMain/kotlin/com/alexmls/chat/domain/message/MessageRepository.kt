package com.alexmls.chat.domain.message

import com.alexmls.chat.domain.models.ChatMessageDeliveryStatus
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.EmptyResult

interface MessageRepository {
    suspend fun updateMessageDeliveryStatus(
        messageId: String,
        status: ChatMessageDeliveryStatus
    ): EmptyResult<DataError.Local>
}