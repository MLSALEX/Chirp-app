package com.alexmls.chat.domain.chat

import com.alexmls.chat.domain.models.Chat
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChats(): Flow<List<Chat>>
    suspend fun fetchChats(): Result<List<Chat>, DataError.Remote>
}