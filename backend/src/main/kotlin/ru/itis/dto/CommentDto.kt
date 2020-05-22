package ru.itis.dto

import kotlin.properties.Delegates

class CommentDto {
    lateinit var username: String
    lateinit var text: String
    var postId by Delegates.notNull<Long>()
}