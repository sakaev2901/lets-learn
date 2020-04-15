package ru.itis.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.itis.jwt.JwtAuthEntryPoint
import ru.itis.jwt.JwtTokenFilter

@Configuration
@EnableWebSecurity
@ComponentScan("ru.itis")
open class SecurityConfig: WebSecurityConfigurerAdapter(){

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    private lateinit var userDetailsService: UserDetailsService
    @Autowired
    private lateinit var jwtAuthEntryPoint: JwtAuthEntryPoint

    @Bean
    open fun jwtTokenFilter() = JwtTokenFilter()

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth
                ?.userDetailsService(userDetailsService)
                ?.passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

}