package ru.itis.services

import ru.itis.dto.CommentDto

interface CommentService {
    fun setComment(commentDto: CommentDto)
}