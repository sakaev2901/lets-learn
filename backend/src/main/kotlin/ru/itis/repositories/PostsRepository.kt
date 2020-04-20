package ru.itis.repositories

import ru.itis.model.Post

interface PostsRepository {
    fun save(post: Post);
    fun findAll(): List<Post>
}