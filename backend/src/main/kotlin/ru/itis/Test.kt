package ru.itis

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ru.itis.config.app.ApplicationContextConfiguration
import ru.itis.model.User
import ru.itis.repositories.UsersRepository
import ru.itis.security.JwtAuthenticationException
import ru.itis.security.TokenProvider
import java.util.*

fun  main() {
//    val applicationContext = AnnotationConfigApplicationContext(ApplicationContextConfiguration::class.java)
//    val usersRepository = applicationContext.getBean(UsersRepository::class.java)
//    println(usersRepository)
//    usersRepository.save(User("Eldar", "1", "ldar"))
//    println(usersRepository.findByUsername("ldar").name)
    val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZGFyIiwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTU4NzExNDY5OCwiZXhwIjoxNTg3MTE0Nzg0fQ.hFH9V4UpI4O0kkZgTRD9PtutASMAjWjoane3goeR97g"

    println(validateToken(token))
}

fun validateToken(token: String):Boolean {
  try {
      val secret = "secretKey"
        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
        if (claims.body.expiration.before(Date())) {
            return false
        }
        return true
    } catch (e: JwtException) {
        throw JwtAuthenticationException("JWT token is expired or invalid")
    } catch (e: IllegalArgumentException) {
        throw JwtAuthenticationException("JWT token is expired or invalid")
    }
}