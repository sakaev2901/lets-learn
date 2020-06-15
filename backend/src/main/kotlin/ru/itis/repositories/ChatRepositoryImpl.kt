package ru.itis.repositories

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.itis.model.Chat
import ru.itis.model.User
import java.lang.Exception
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
@Transactional
open class ChatRepositoryImpl: ChatRepository {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    override fun save(chat: Chat) {
        entityManager.persist(chat)
    }

    override fun findByName(name: String): Chat {

            return entityManager.createQuery("select c from Chat c where c.name = :name", Chat::class.java)
                    .setParameter("name", name)
                    .singleResult

    }

    override fun findUserChats(user: User): List<Chat> {
        return entityManager.createQuery("select c from Chat c where c.firstUser = :user or c.secondUser = :user", Chat::class.java)
                .setParameter("user", user)
                .resultList
    }
}