package ru.itis

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ru.itis.config.app.ApplicationContextConfiguration
import ru.itis.model.User
import ru.itis.repositories.UsersRepository
import ru.itis.repositories.UsersRepositoryJpaImpl
import ru.itis.security.JwtAuthenticationException
import java.util.*

fun  main() {
    val ldar = User()
    val dmr = User()
    ldar.friends = arrayListOf(dmr)
    dmr.friends = arrayListOf(ldar)
    val json = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(ldar.friends)
    println(json)
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