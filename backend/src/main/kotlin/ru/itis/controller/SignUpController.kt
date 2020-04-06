package ru.itis.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.itis.dto.UserDto
import ru.itis.model.User
import java.lang.reflect.Member

@RestController("/api")
class SignUpController {

    @PostMapping("/signUp"   )
    fun getFields(@RequestBody userDto: UserDto){
        println(5)
    }


}