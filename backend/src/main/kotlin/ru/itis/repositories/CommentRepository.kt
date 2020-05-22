package ru.itis.repositories

import ru.itis.model.Comment

interface CommentRepository {
    fun save(comment: Comment)
}