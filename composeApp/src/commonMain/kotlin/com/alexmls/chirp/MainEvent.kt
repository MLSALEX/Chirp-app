package com.alexmls.chirp

sealed interface MainEvent {
    data object OnSessionExpired: MainEvent
}