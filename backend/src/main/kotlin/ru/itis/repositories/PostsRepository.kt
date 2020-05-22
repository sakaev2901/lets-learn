package ru.itis.repositories

import ru.itis.model.Post
import ru.itis.model.User

interface PostsRepository {
    fun save(post: Post);
    fun findAll(): List<Post>
    fun findByUser(user: User): List<Post>
    fun updateLikes(post: Post)
    fun findById(id: Long): Post
}