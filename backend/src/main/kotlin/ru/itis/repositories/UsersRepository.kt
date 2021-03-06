package ru.itis.repositories

import org.springframework.data.repository.CrudRepository
import ru.itis.dto.ProfileDto
import ru.itis.model.User

interface UsersRepository {
    fun save(model: User)
    fun findByUsername(name: String?):User?
    fun findAllFriendsByUsername(name: String?): List<User>?
    fun saveFriend(user: User)
    fun findSimpleUserByUsername(username: String): ProfileDto
}