package com.alexmls.chat.database

import androidx.room.Room
import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<ChirpChatDatabase>
}