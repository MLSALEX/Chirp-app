package com.alexmls.chat.presentation.create_chat

import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chirp.feature.chat.presentation.generated.resources.Res
import chirp.feature.chat.presentation.generated.resources.error_participant_not_found
import com.alexmls.chat.domain.chat.ChatParticipantService
import com.alexmls.chat.presentation.mappers.toUi
import com.alexmls.core.domain.util.DataError
import com.alexmls.core.presentation.util.UiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.alexmls.core.presentation.util.toUiText
import com.alexmls.core.domain.util.onFailure
import com.alexmls.core.domain.util.onSuccess
import kotlinx.coroutines.flow.onEach

@OptIn(FlowPreview::class)
class CreateChatViewModel(
    private val chatParticipantService: ChatParticipantService
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(CreateChatState())

    private val searchFlow = snapshotFlow { _state.value.queryTextState.text.toString() }
        .debounce(1.seconds)
        .onEach { query ->
            performSearch(query)
        }

    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                searchFlow.launchIn(viewModelScope)
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CreateChatState()
        )

    fun onAction(action: CreateChatAction) {
        when (action) {
            CreateChatAction.OnAddClick -> addParticipant()
            CreateChatAction.OnCreateChatClick -> {

            }
            CreateChatAction.OnDismissDialog -> Unit
        }
    }
    private fun addParticipant() {
        state.value.currentSearchResult?.let { participant ->
            val isAlreadyPartOfChat = state.value.selectedChatParticipants.any {
                it.id == participant.id
            }
            if(!isAlreadyPartOfChat) {
                _state.update { it.copy(
                    selectedChatParticipants = it.selectedChatParticipants + participant,
                    canAddParticipant = false,
                    currentSearchResult = null
                ) }
                _state.value.queryTextState.clearText()
            }
        }
    }

    private fun performSearch(query: String) {
        if (query.isBlank()) {
            _state.update { it.copy(
                currentSearchResult = null,
                canAddParticipant = false,
                searchError = null
            ) }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(
                isSearching = true,
                canAddParticipant = false
            ) }

            chatParticipantService
                .searchParticipant(query)
                .onSuccess { participant ->
                    _state.update { it.copy(
                        currentSearchResult = participant.toUi(),
                        isSearching = false,
                        canAddParticipant = true,
                        searchError = null
                    ) }
                }
                .onFailure { error ->
                    val errorMessage = when(error) {
                        DataError.Remote.NOT_FOUND -> UiText.Resource(Res.string.error_participant_not_found)
                        else -> error.toUiText()
                    }
                    _state.update { it.copy(
                        searchError = errorMessage,
                        isSearching = false,
                        canAddParticipant = false,
                        currentSearchResult = null
                    ) }
                }
        }
    }
}