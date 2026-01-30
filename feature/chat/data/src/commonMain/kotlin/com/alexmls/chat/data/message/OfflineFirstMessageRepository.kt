package com.alexmls.chat.data.message

import com.alexmls.chat.database.ChirpChatDatabase
import com.alexmls.chat.domain.message.MessageRepository
import com.alexmls.chat.domain.models.ChatMessage
import com.alexmls.chat.domain.models.ChatMessageDeliveryStatus
import com.alexmls.core.data.database.safeDatabaseUpdate
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.EmptyResult
import kotlin.time.Clock
import com.alexmls.core.domain.util.Result
import com.alexmls.core.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.alexmls.chat.data.mappers.toDomain
import com.alexmls.chat.data.mappers.toEntity
import com.alexmls.chat.domain.message.ChatMessageService
import com.alexmls.chat.domain.models.MessageWithSender

class OfflineFirstMessageRepository(
    private val database: ChirpChatDatabase,
    private val chatMessageService: ChatMessageService
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

    override suspend fun fetchMessages(
        chatId: String,
        before: String?
    ): Result<List<ChatMessage>, DataError> {
        return chatMessageService
            .fetchMessages(chatId, before)
            .onSuccess { messages ->
                return safeDatabaseUpdate {
                    database.chatMessageDao.upsertMessagesAndSyncIfNecessary(
                        chatId = chatId,
                        serverMessages = messages.map { it.toEntity() },
                        pageSize = ChatMessageConstants.PAGE_SIZE,
                        shouldSync = before == null // Only sync for most recent page
                    )
                    messages
                }
            }
    }

    override fun getMessagesForChat(chatId: String): Flow<List<MessageWithSender>> {
        return database
            .chatMessageDao
            .getMessagesByChatId(chatId)
            .map { messages ->
                messages.map { it.toDomain() }
            }
    }
}
