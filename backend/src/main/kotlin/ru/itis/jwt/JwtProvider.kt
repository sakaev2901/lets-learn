package ru.itis.jwt

import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.itis.repositories.UsersRepository
import java.lang.Exception
import java.lang.IllegalStateException
import java.util.*


@Component
class JwtProvider {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Value("\${jwt.secret}")
    lateinit var jwtSecret: String

    @Value("\${jwt.expiration}")
    var jwtExpiration = 0

    fun generateJwtToken(username: String): String {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact()
    }

    fun validateJwtToken(token: String): Boolean {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            return true
    }

    fun getUsernameFromJwtToken(token: String) = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body.subject
}