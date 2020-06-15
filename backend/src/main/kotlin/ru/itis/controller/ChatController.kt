package ru.itis.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import ru.itis.dto.MessageDto
import ru.itis.model.Message
import ru.itis.repositories.ChatRepository
import ru.itis.repositories.UsersRepository
import ru.itis.services.MessageService
import java.lang.IllegalStateException

@Controller
class ChatController {


    @Autowired
    lateinit var usersRepository: UsersRepository
    @Autowired
    lateinit var messageService: MessageService
    @Autowired
    lateinit var chatRepository: ChatRepository

    @MessageMapping("/message/{room}")
    fun chatting(@DestinationVariable room: String, messageDto: MessageDto){
        messageService.receiveMessage(messageDto, room)
    }
}