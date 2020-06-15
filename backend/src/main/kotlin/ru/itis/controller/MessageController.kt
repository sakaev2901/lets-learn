package ru.itis.controller

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itis.model.Chat
import ru.itis.repositories.ChatRepository
import ru.itis.services.MessageService

@RestController
@RequestMapping("/api")
class MessageController {
    @Autowired
    lateinit var messageService: MessageService

    @GetMapping("/user/chat/{room}")
    fun getChat(@PathVariable room: String): Chat? {
        return messageService.getChat(room)
    }

    @GetMapping("/messages/{room}")
    fun getMessages(@PathVariable room: String) = messageService.getChatMessages(room)
}