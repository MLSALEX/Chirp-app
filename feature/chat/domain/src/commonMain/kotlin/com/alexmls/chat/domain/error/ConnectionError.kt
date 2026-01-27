package com.alexmls.chat.domain.error

import com.alexmls.core.domain.util.Error

enum class ConnectionError: Error {
    NOT_CONNECTED,
    MESSAGE_SEND_FAILED
}