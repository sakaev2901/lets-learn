package ru.itis.dto

import kotlin.properties.Delegates

class LikeDto {
    lateinit var username: String
    var postId by Delegates.notNull<Long>()
}