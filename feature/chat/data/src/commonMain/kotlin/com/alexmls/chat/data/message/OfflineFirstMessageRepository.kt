package com.alexmls.chat.data.message

import com.alexmls.chat.database.ChirpChatDatabase
import com.alexmls.chat.domain.message.MessageRepository
import com.alexmls.chat.domain.models.ChatMessageDeliveryStatus
import com.alexmls.core.data.database.safeDatabaseUpdate
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.EmptyResult
import kotlin.time.Clock

class OfflineFirstMessageRepository(
    private val database: ChirpChatDatabase
): MessageRepository {

    override suspend fun updateMessageDeliveryStatus(
        messageId: String,
        status: ChatMessageDeliveryStatus
    ): EmptyResult<DataError.Local> {
        return safeDatabaseUpdate {
            database.chatMessageDao.updateDeliveryStatus(
                messageId = messageId,
                status = status.name,
                timestamp = Clock.System.now().toEpochMilliseconds()
            )
        }
    }
}
