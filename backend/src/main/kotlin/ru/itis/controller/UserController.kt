package ru.itis.controller

import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.itis.dto.FriendDto
import ru.itis.dto.ProfileDto
import ru.itis.model.Chat
import ru.itis.model.User
import ru.itis.repositories.ChatRepository
import ru.itis.repositories.UsersRepository
import ru.itis.services.UserService
import java.lang.IllegalStateException
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class UserController {

    @Autowired
    lateinit var usersRepository: UsersRepository
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var chatRepository: ChatRepository

    @GetMapping("/user/{username}")
    fun getUser(@PathVariable username: String) = usersRepository.findByUsername(username)?: throw IllegalStateException()



    @GetMapping("/friends/{username}")
    fun getUserFriends(@PathVariable username: String): List<User>? {
        val friends = usersRepository.findAllFriendsByUsername(username)
        return friends
    }

    @PostMapping("/addFriend")
    fun addUser(@RequestBody friendDto: FriendDto) {
        val user = usersRepository.findByUsername(friendDto.username)?: throw IllegalStateException()
        usersRepository.saveFriend(user)
    }

    @GetMapping("/profile/{username}")
    fun getSimpleUser(@PathVariable username: String): ProfileDto {
        return usersRepository.findSimpleUserByUsername(username)
    }

    @GetMapping("/profile/image/{filename:.+}")
    fun getImage(@PathVariable filename: String,  response: HttpServletResponse) {
        IOUtils.copy(userService.getAvatar(filename), response.outputStream)
        response.flushBuffer()
    }

    @GetMapping("/userChats/{username}")
    fun getChats(@PathVariable username: String): List<Chat> {
        return chatRepository.findUserChats(usersRepository.findByUsername(username)!!)
    }
}