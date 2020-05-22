package ru.itis.services

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.itis.dto.CommentDto
import ru.itis.dto.LikeDto
import ru.itis.model.Comment
import ru.itis.model.Post
import ru.itis.model.User
import ru.itis.repositories.PostsRepository
import ru.itis.repositories.UsersRepository
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.lang.IllegalStateException
import java.nio.file.Paths

@Service
class PostsServiceImpl: PostsService {

    @Autowired
    private lateinit var postsRepository: PostsRepository
    @Autowired
    lateinit var usersRepository: UsersRepository

    override fun savePost(title: String, text: String, multipartFile: MultipartFile): Post {
        val post = Post()
        post.imageName = savePhoto(multipartFile)
        post.title = title
        post.text = text
        println("Name " + SecurityContextHolder.getContext().authentication.name)
        post.user = usersRepository.findByUsername(SecurityContextHolder.getContext().authentication.name)
        postsRepository.save(post)
        return post
    }

    override fun getAllPosts() = postsRepository.findAll()

    override fun getImageInputStream(imageName: String): InputStream {
        return FileInputStream(File("C:\\Projects\\lets-learn\\frontend\\src\\assets\\img\\post\\$imageName"))
    }

    override fun getUserPosts(username: String): List<Post> {
        println(username)
        return postsRepository.findByUser(usersRepository.findByUsername(username)!!)
    }

    override fun setLike(likeDto: LikeDto) {
        val user = usersRepository.findByUsername(likeDto.username)?: throw IllegalStateException()
        val post = postsRepository.findById(likeDto.postId)
        post.addLike(user)
        postsRepository.updateLikes(post)
    }

    override fun dislike(likeDto: LikeDto) {
        val post = postsRepository.findById(likeDto.postId)
        println(post.likes[0].username)
        println(likeDto.username)
//        post.likes.filter { user -> !user.username.equals(likeDto.username) }
        lateinit var candidate: User
        post.likes.iterator().forEach {
            if (it.username == likeDto.username) {
                candidate = it
            }
        }
        post.likes.remove(candidate)
        println(post.likes.size )
        postsRepository.updateLikes(post)
    }



    private fun generateImageName(imageName: String): String {
        val extension:String = imageName.split(".")[1]
        return RandomStringUtils.randomAlphabetic(10) + "." + extension

    }

    private fun savePhoto(multipartFile: MultipartFile): String {
        val newName = generateImageName(multipartFile.originalFilename)
        val path = Paths.get("C:\\Projects\\lets-learn\\frontend\\src\\assets\\img\\post\\$newName")
        multipartFile.transferTo(path)
        return newName
    }
}