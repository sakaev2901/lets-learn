package ru.itis.repositories

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.itis.model.Post
import ru.itis.model.User
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
@Transactional
open class PostsRepositoryImpl: PostsRepository {


    @PersistenceContext
    lateinit var entityManager: EntityManager


    @Transactional
    override fun save(post: Post) {
        entityManager.persist(post)
    }

    override fun findAll() = entityManager.createQuery("select c from Post c", Post::class.java).resultList
    override fun findByUser(user: User): List<Post> {
        return entityManager.createQuery("select c from  Post c where c.user = :user", Post::class.java)
                .setParameter("user", user)
                .resultList
    }


    override fun updateLikes(post: Post) {
        entityManager.merge(post)
    }

    override fun findById(id: Long): Post {
        return entityManager.createQuery("select c from Post c where c.id = :id", Post::class.java)
                .setParameter("id", id)
                .singleResult
    }

}