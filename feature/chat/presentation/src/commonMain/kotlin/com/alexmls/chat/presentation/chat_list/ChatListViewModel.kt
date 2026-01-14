package com.alexmls.chat.presentation.chat_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmls.core.domain.auth.SessionStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatListViewModel(
    private val sessionStorage: SessionStorage
): ViewModel() {

    // TEST ONLY: simulate expired session by clearing SessionStorage
    init {
        viewModelScope.launch {
            delay(5_000)
            sessionStorage.set(null)
        }
    }
}