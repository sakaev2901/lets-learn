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


@CrossOrigin
@RestController
@RequestMapping("/api")
class SignUpController {

    @Autowired
    lateinit var usersRepository: UsersRepository

//    @PostMapping("/signUp", consumes = ["application/json"], produces = ["application/json"])
//    fun getFields(@RequestBody newUser: User): ResponseEntity<*>{
//        val userCandidate: User
//        val user = User()
//                .apply { login = newUser.login }
//                .apply { mail = newUser.mail }
//                .apply { password = newUser.password}
//        usersRepository.save(user)
//        println(5)
//        return ResponseEntity(ResponseMessage("Success") ,HttpStatus.OK)
//    }

    @PostMapping("/signUp")
    fun getFields() {
        println(5)
    }


}