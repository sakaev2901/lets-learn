package ru.itis.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import ru.itis.dto.MessageDto
import ru.itis.model.Chat
import ru.itis.model.Message
import ru.itis.model.User
import ru.itis.repositories.ChatRepository
import ru.itis.repositories.MessageRepository
import ru.itis.repositories.UsersRepository
import java.lang.Exception
import java.lang.IllegalStateException

@Service
class MessageServiceImpl : MessageService{

    @Autowired
    lateinit var messageRepository: MessageRepository
    @Autowired
    lateinit var usersRepository: UsersRepository
    @Autowired
    lateinit var chatRepository: ChatRepository
    @Autowired
    lateinit var simpMessagingTemplate: SimpMessagingTemplate

    override fun receiveMessage(messageDto: MessageDto, room: String) {
        val message = Message()
        message.sender = usersRepository.findByUsername(messageDto.senderUsername)?: throw IllegalStateException()
        message.receiver = usersRepository.findByUsername(messageDto.receiverUsername)?: throw IllegalStateException()
        message.text = messageDto.text
        message.chat = chatRepository.findByName(room)
        println(messageDto.receiverUsername)
        simpMessagingTemplate.convertAndSend("/chat/", message)
        simpMessagingTemplate.convertAndSend("/chat/$room", message)
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

    override fun getChat(name: String): Chat {
        return try {
            chatRepository.findByName(name)
        } catch (e: Exception) {
            val chat = Chat()
            chat.name = name
            val names = name.split('-')
            chat.firstUser = usersRepository.findByUsername(names[0])!!
            chat.secondUser = usersRepository.findByUsername(names[1])!!
            chatRepository.save(chat)
            chatRepository.findByName(name)
        }
    }

    override fun getChatMessages(name: String): List<Message> {
        return messageRepository.findChatMessages(chatRepository.findByName(name))
    }
}