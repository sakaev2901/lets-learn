package ru.itis.services

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.itis.model.Post
import ru.itis.model.User
import ru.itis.repositories.PostsRepository
import ru.itis.repositories.UsersRepository
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
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