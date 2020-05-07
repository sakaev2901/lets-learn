package ru.itis.controller

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itis.services.MessageService

@RestController
@RequestMapping("/api")
class MessageController {
    @Autowired
    lateinit var messageService: MessageService


    @GetMapping("/messages/{firstUser}-{secondUser}")
    fun getMessages(@PathVariable firstUser: String, @PathVariable secondUser: String) = messageService.getAllRoomMessage(
            firstUser, secondUser
    )
}