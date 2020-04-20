package ru.itis.controller

import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.itis.model.Post
import ru.itis.services.PostsService
import javax.servlet.http.HttpServletResponse

@CrossOrigin
@RestController
@RequestMapping("/api")
class PostsController {

    @Autowired
    lateinit var postsService: PostsService

    @CrossOrigin
    @PostMapping("/post")
    fun addPost(@RequestParam("image") multipartFile: MultipartFile,
                @RequestParam("title") title: String,
                @RequestParam("text") text: String): ResponseEntity<*> {
        val addedPost = postsService.savePost(title, text, multipartFile)
        return ResponseEntity.ok(addedPost)
    }

    @GetMapping("/allPosts")
    fun getPosts() = postsService.getAllPosts()

    @GetMapping("/image/{img-name:.+}")
    fun getImage(@PathVariable("img-name") imageName: String, response: HttpServletResponse) {
        IOUtils.copy(postsService.getImageInputStream(imageName), response.outputStream)
        response.flushBuffer()
    }
}