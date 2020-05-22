package ru.itis.services

import java.io.InputStream

interface UserService {

    fun getAvatar(filename: String): InputStream
}