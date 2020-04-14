package ru.itis.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.itis.repositories.UsersRepository

@Service
class UserDetailsServiceImpl: UserDetailsService {

    @Autowired
    private lateinit var usersRepository: UsersRepository

    override fun loadUserByUsername(username: String?) = usersRepository.findByUsername(username)
}