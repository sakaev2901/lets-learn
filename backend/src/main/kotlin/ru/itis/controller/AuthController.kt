package ru.itis.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itis.dto.AuthDto
import ru.itis.model.NewUser
import ru.itis.model.User
import ru.itis.repositories.UsersRepository
import ru.itis.security.TokenProvider
import ru.itis.web.response.JwtResponse
import ru.itis.web.response.ResponseMessage

@RestController
@RequestMapping("/api")
class AuthController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var usersRepository: UsersRepository
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var tokenProvider: TokenProvider

    @PostMapping("/signIn")
    fun signIn(@RequestBody authDto: AuthDto): ResponseEntity<*> {
        println(authDto.username)
        val user = usersRepository.findByUsername(authDto.username)
                ?: return ResponseEntity(ResponseMessage("User not found"), HttpStatus.BAD_REQUEST)
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authDto.username, authDto.password))
        SecurityContextHolder.getContext().authentication = authentication
        val token = tokenProvider.createToken(user.username, user.role)!!
        val jwtResponse = JwtResponse(token, user.username, user.role, user.name, user.surname, user.imageName)
        println(jwtResponse)
        return ResponseEntity.ok(jwtResponse)
    }

    @PostMapping("/signUp")
    fun signUp(@RequestBody newUser: NewUser) {
        val user = User(newUser.name, newUser.surname, newUser.mail, newUser.username, passwordEncoder.encode(newUser.password), "ROLE_USER")
        usersRepository.save(user)
    }
}