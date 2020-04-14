package ru.itis.services

import org.springframework.web.multipart.MultipartFile
import ru.itis.model.Post

interface PostsService {
    fun savePost(title:String, text:String, multipartFile: MultipartFile);
}