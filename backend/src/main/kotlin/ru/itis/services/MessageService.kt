package ru.itis.services

import ru.itis.model.Message
import ru.itis.model.User

interface MessageService {

    fun saveMessage(message: Message)
    fun getAllRoomMessage(firstUsername: String, secondUsername: String): List<Message>
}