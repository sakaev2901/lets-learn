//package ru.itis.jwt
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.web.authentication.WebAuthenticationDetails
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
//import org.springframework.web.filter.OncePerRequestFilter
//import javax.servlet.FilterChain
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
//class JwtTokenFilter: OncePerRequestFilter() {
//
//    @Autowired
//    lateinit var tokenProvider: JwtProvider
//    @Autowired
//    lateinit var userDetailsService: UserDetailsService
//
//    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
//        val jwt = getJwt(request)
//        println(jwt)
//            if(jwt != null && tokenProvider.validateJwtToken(jwt)) {
//                val username = tokenProvider.getUsernameFromJwtToken(jwt)
//                val userDetails = userDetailsService.loadUserByUsername(username)
//                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
//                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
//                SecurityContextHolder.getContext().authentication = authentication
//            }
//        filterChain.doFilter(request, response)
//    }
//
//    private fun getJwt(request: HttpServletRequest): String? {
//        val autHeader = request.getHeader("Authorization")
//        return if (autHeader != null && autHeader.startsWith("Bearer") && autHeader.length > 8) {
//            autHeader.replace("Bearer ", "")
//        } else null
//    }
//
//}