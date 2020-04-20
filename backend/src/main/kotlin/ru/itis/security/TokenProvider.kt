package ru.itis.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class TokenProvider {
    @Value("\${jwt.secret}")
    private var secret: String? = null

    @Value("\${jwt.expiration}")
    private val validityInMilliseconds: Long = 0


    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @PostConstruct
    private fun init() {
        secret = Base64.getEncoder().encodeToString(secret!!.toByteArray())
    }

    fun createToken(username: String?, role: String?): String? {
        val claims = Jwts.claims().setSubject(username)
        claims["role"] = role
        val now = Date()
        val validity = Date(now.getTime() + validityInMilliseconds)
        return Jwts.builder() //
                .setClaims(claims) //
                .setIssuedAt(now) //
                .setExpiration(validity) //
                .signWith(SignatureAlgorithm.HS256, secret) //
                .compact()
    }

    fun getAuthentication(token: String?): Authentication? {
        val userDetails = userDetailsService!!.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getUsername(token: String?): String? {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    fun validateToken(token: String?): Boolean {
        return try {
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

}