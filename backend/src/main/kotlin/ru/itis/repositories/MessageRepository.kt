package ru.itis.repositories

import ru.itis.model.Message
import ru.itis.model.User

interface MessageRepository {
    fun save(message: Message)
    fun findAllRoomMessages(firstUser: User, secondUser: User): List<Message>
}