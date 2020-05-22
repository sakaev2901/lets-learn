package ru.itis.services

import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

@Service
class UserServiceImpl: UserService {
    override fun getAvatar(filename: String): InputStream {
        return FileInputStream(File("C:\\Projects\\lets-learn\\frontend\\src\\assets\\img\\profile\\$filename"))
    }
}