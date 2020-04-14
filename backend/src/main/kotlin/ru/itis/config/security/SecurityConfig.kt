package ru.itis.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@ComponentScan
open class SecurityConfig: WebSecurityConfigurerAdapter(){

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth
                ?.userDetailsService(userDetailsService)
                ?.passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity?) {
        http!!
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/profile", "/main")
                .hasRole("USER")
                .antMatchers("/**")
                .permitAll()
    }


}