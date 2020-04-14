package ru.itis.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itis.dto.UserDto
import ru.itis.model.NewUser
import ru.itis.model.User
import ru.itis.repositories.UsersRepository
import ru.itis.web.response.ResponseMessage
import java.lang.reflect.Member
import javax.servlet.http.HttpServletRequest


@CrossOrigin
@RestController
@RequestMapping("/api")
class SignUpController {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @CrossOrigin
    @PostMapping("/signUp")
     fun getField(@RequestBody newUser: User) {
        println(newUser.mail)
    }


}