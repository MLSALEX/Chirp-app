package com.alexmls.chat.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexmls.chat.database.dao.ChatDao
import com.alexmls.chat.database.dao.ChatMessageDao
import com.alexmls.chat.database.dao.ChatParticipantDao
import com.alexmls.chat.database.dao.ChatParticipantsCrossRefDao
import com.alexmls.chat.database.entities.ChatEntity
import com.alexmls.chat.database.entities.ChatMessageEntity
import com.alexmls.chat.database.entities.ChatParticipantCrossRef
import com.alexmls.chat.database.entities.ChatParticipantEntity
import com.alexmls.chat.database.view.LastMessageView

@Database(
    entities = [
        ChatEntity::class,
        ChatParticipantEntity::class,
        ChatMessageEntity::class,
        ChatParticipantCrossRef::class,
    ],
    views = [
        LastMessageView::class
    ],
    version = 1,
)
abstract class ChirpChatDatabase: RoomDatabase() {
    abstract val chatDao: ChatDao
    abstract val chatParticipantDao: ChatParticipantDao
    abstract val chatMessageDao: ChatMessageDao
    abstract val chatParticipantsCrossRefDao: ChatParticipantsCrossRefDao

    companion object {
        const val DB_NAME = "chirp.db"
    }
}