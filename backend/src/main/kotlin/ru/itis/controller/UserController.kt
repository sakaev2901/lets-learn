package ru.itis.controller

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itis.model.User
import ru.itis.repositories.UsersRepository
import java.lang.IllegalStateException

@RestController
@RequestMapping("/api")
class UserController {

    @Autowired
    lateinit var usersRepository: UsersRepository
    @Autowired
    lateinit var gson: Gson

    @GetMapping("/user/{username}")
    fun getUser(@PathVariable username: String): User {
        return usersRepository.findByUsername(username)?: throw IllegalStateException()

    }


    @GetMapping("/friends/{username}")
    fun getUserFriends(@PathVariable username: String): List<User>? {
        val friends = usersRepository.findAllFriendsByUsername(username)
        return friends
    }
}