package ru.itis.services

import org.springframework.web.multipart.MultipartFile
import ru.itis.dto.CommentDto
import ru.itis.dto.LikeDto
import ru.itis.model.Post
import ru.itis.model.User
import java.io.InputStream

interface PostsService {
    fun savePost(title:String, text:String, multipartFile: MultipartFile): Post;
    fun getAllPosts(): List<Post>
    fun getImageInputStream(imageName: String): InputStream
    fun getUserPosts(username: String): List<Post>
    fun setLike(likeDto: LikeDto)
    fun dislike(likeDto: LikeDto)
}