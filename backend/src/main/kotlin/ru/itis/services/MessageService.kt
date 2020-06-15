package ru.itis.services

import ru.itis.dto.MessageDto
import ru.itis.model.Chat
import ru.itis.model.Message
import ru.itis.model.User

interface MessageService {

    fun receiveMessage(messageDto: MessageDto, room: String)
    fun getAllRoomMessage(firstUsername: String, secondUsername: String): List<Message>
    fun getChat(name: String): Chat
    fun getChatMessages(name: String): List<Message>
}