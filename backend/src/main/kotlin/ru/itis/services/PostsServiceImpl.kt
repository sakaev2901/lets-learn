package ru.itis.services

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.itis.model.Post
import ru.itis.repositories.PostsRepository
import java.nio.file.Paths

@Service
class PostsServiceImpl: PostsService {

    @Autowired
    private lateinit var postsRepository: PostsRepository

    override fun savePost(title: String, text: String, multipartFile: MultipartFile) {
        val post = Post()
        post.imageName = savePhoto(multipartFile)
        post.title = title
        post.text = text
        postsRepository.save(post)
    }

    private fun generateImageName(imageName: String): String {
        val extension:String = imageName.split(".")[1]
        return RandomStringUtils.randomAlphabetic(10) + "." + extension

    }

    private fun savePhoto(multipartFile: MultipartFile): String {
        val newName = generateImageName(multipartFile.originalFilename)
        val path = Paths.get("/home/ldar/upload/$newName")
        multipartFile.transferTo(path)
        return newName
    }
}