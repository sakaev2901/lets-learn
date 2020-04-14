package ru.itis.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.itis.model.Post
import ru.itis.services.PostsService

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
                @RequestParam("text") text: String) {
        postsService.savePost(title, text, multipartFile)
    }
}