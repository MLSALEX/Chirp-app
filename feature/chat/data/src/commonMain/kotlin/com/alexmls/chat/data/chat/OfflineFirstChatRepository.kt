package com.alexmls.chat.data.chat

import com.alexmls.chat.data.mappers.toDomain
import com.alexmls.chat.data.mappers.toEntity
import com.alexmls.chat.data.mappers.toLastMessageView
import com.alexmls.chat.database.ChirpChatDatabase
import com.alexmls.chat.database.entities.ChatWithParticipants
import com.alexmls.chat.domain.chat.ChatRepository
import com.alexmls.chat.domain.chat.ChatService
import com.alexmls.chat.domain.models.Chat
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.alexmls.core.domain.util.Result

class OfflineFirstChatRepository(
    private val chatService: ChatService,
    private val db: ChirpChatDatabase
): ChatRepository {

    override fun getChats(): Flow<List<Chat>> {
        return db.chatDao.getChatsWithActiveParticipants()
            .map { chatWithParticipantsList ->
                chatWithParticipantsList.map { it.toDomain() }
            }
    }

    override suspend fun fetchChats(): Result<List<Chat>, DataError.Remote> {
        return chatService
            .getChats()
            .onSuccess { chats ->
                val chatsWithParticipants = chats.map { chat ->
                    ChatWithParticipants(
                        chat = chat.toEntity(),
                        participants = chat.participants.map { it.toEntity() },
                        lastMessage = chat.lastMessage?.toLastMessageView()
                    )
                }

                db.chatDao.upsertChatsWithParticipantsAndCrossRefs(
                    chats = chatsWithParticipants,
                    participantDao = db.chatParticipantDao,
                    crossRefDao = db.chatParticipantsCrossRefDao,
                    messageDao = db.chatMessageDao
                )
            }
    }
}