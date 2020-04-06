package ru.itis.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.itis.model.User

@RestController
class SignInController {

    @PostMapping("/sign")
    fun getUser(@RequestBody user: User) {
        println(user.name)
        println(user.id)
    }
}