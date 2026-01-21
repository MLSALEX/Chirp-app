package com.alexmls.chat.presentation.chat_list

import com.alexmls.chat.presentation.model.ChatUi
import com.alexmls.core.designsystem.components.avatar.ChatParticipantUi
import com.alexmls.core.presentation.util.UiText

data class ChatListState(
    val chats: List<ChatUi> = emptyList(),
    val error: UiText? = null,
    val localParticipant: ChatParticipantUi? = null,
    val isUserMenuOpen: Boolean = false,
    val showLogoutConfirmation: Boolean = false,
    val selectedChatId: String? = null,
    val isLoading: Boolean = false,
)