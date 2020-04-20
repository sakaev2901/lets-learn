package ru.itis.services

import org.springframework.web.multipart.MultipartFile
import ru.itis.model.Post
import java.io.InputStream

interface PostsService {
    fun savePost(title:String, text:String, multipartFile: MultipartFile): Post;
    fun getAllPosts(): List<Post>
    fun getImageInputStream(imageName: String): InputStream
}