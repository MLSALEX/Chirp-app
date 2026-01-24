package com.alexmls.chat.domain.chat

import com.alexmls.chat.domain.models.Chat
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.Result

interface ChatService {
    suspend fun createChat(
        otherUserIds: List<String>
    ): Result<Chat, DataError.Remote>

    suspend fun getChats(): Result<List<Chat>, DataError.Remote>
}