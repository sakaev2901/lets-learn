package ru.itis.repositories

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.itis.model.Comment
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
@Transactional
open class CommentRepositoryImpl : CommentRepository {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    override fun save(comment: Comment) {
        entityManager.persist(comment)
    }
}