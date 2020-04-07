package ru.itis.repositories

import org.springframework.data.repository.CrudRepository
import ru.itis.model.User

interface UsersRepository {
    fun save(model: User)
    fun findByName(name: String):User
}