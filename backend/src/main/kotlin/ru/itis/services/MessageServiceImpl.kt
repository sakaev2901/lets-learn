package ru.itis.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.itis.model.Message
import ru.itis.model.User
import ru.itis.repositories.MessageRepository
import ru.itis.repositories.UsersRepository
import java.lang.IllegalStateException

@Service
class MessageServiceImpl : MessageService{

    @Autowired
    lateinit var messageRepository: MessageRepository
    @Autowired
    lateinit var usersRepository: UsersRepository

    override fun saveMessage(message: Message) {
        println("serv")
        messageRepository.save(message)
    }

    override fun getAllRoomMessage(firstUsername: String, secondUsername: String): List<Message> {
        println(firstUsername)
        println(secondUsername)
        return messageRepository.findAllRoomMessages(
                usersRepository.findByUsername(firstUsername) ?: throw IllegalStateException(),
                usersRepository.findByUsername(secondUsername) ?: throw IllegalStateException()
        )
    }
}