package ru.itis.repositories

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.itis.dto.ProfileDto
import ru.itis.model.User
import java.sql.ResultSet
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Component
open class UsersRepositoryJpaImpl: UsersRepository {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Transactional
    override fun save(model: User) = entityManager.persist(model)

    override fun findByUsername(name: String?): User? = entityManager
            .createQuery("select c from User c where c.username = :username", User::class.java)
            .setParameter("username", name)
            .singleResult

    override fun findAllFriendsByUsername(name: String?): List<User>? {
        val user = entityManager
                .createQuery("select c from User c where c.username = :username", User::class.java)
                .setParameter("username", name)
                .singleResult
        return user.friends
    }

    @Transactional
    override fun saveFriend(friend: User) {
        val user = entityManager
                .createQuery("select c from User c where c.username = :username", User::class.java)
                .setParameter("username", "ldar")
                .singleResult
        user.addFriend(friend)
        entityManager.merge(user)
    }

    override fun findSimpleUserByUsername(username: String): ProfileDto {
        val user = entityManager.createQuery("select c from User c where c.username = :username", User::class.java)
                .setParameter("username", username)
                .singleResult
        println("Profile DTO")
        val profileDto = ProfileDto()
        profileDto.name = user.name
        profileDto.surname = user.surname
        println(profileDto.name)
        return profileDto

    }


}


