package ru.itis.controller

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.itis.dto.MessageDto
import ru.itis.model.Message
import ru.itis.repositories.UsersRepository
import ru.itis.services.MessageService
import java.lang.IllegalStateException
import java.nio.file.attribute.GroupPrincipal
import java.security.Principal

@Controller
class ChatController {

    @Autowired
    lateinit var simpMessagingTemplate: SimpMessagingTemplate
    @Autowired
    lateinit var usersRepository: UsersRepository
    @Autowired
    lateinit var messageService: MessageService
    @Autowired
    lateinit var gson: Gson

    @MessageMapping("/message/{room}")
    fun chatting(@DestinationVariable room: String, messageDto: MessageDto){
        val message = Message()
        message.sender = usersRepository.findByUsername(messageDto.senderUsername)?: throw IllegalStateException()
        message.receiver = usersRepository.findByUsername(messageDto.receiverUsername)?: throw IllegalStateException()
        message.text = messageDto.text
        println(messageDto.receiverUsername)
        messageService.saveMessage(message)
        simpMessagingTemplate.convertAndSend("/chat/$room", message)
    }
}