package ru.itis.repositories

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.itis.model.Post
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
open class PostsRepositoryImpl: PostsRepository {


    @PersistenceContext
    lateinit var entityManager: EntityManager


    @Transactional
    override fun save(post: Post) {
        entityManager.persist(post)
    }

}