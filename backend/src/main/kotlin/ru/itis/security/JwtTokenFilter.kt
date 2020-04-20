package ru.itis.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter(private val tokenProvider: TokenProvider) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = tokenProvider.resolveToken(request as HttpServletRequest)
        println(token)
            if (token != null && tokenProvider.validateToken(token)) {
                val authentication = tokenProvider.getAuthentication(token)
                if (authentication != null) {
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        filterChain?.doFilter(request, response)
    }


}