package ru.itis.repositories

import org.springframework.stereotype.Component
import ru.itis.model.User
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Component
open class UsersRepositoryJpaImpl: UsersRepository {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Transactional
    override fun save(model: User) = entityManager.persist(model)

    override fun findByName(name: String): User = entityManager.find(User::class.java, 1);

}