package ru.itis.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.itis.dto.CommentDto
import ru.itis.model.Comment
import ru.itis.repositories.CommentRepository
import ru.itis.repositories.PostsRepository
import ru.itis.repositories.UsersRepository
import java.lang.IllegalStateException

@Service
class CommentServiceImpl : CommentService {

    @Autowired
    lateinit var commentRepository: CommentRepository
    @Autowired
    lateinit var usersRepository: UsersRepository
    @Autowired
    lateinit var postsRepository: PostsRepository

    override fun setComment(commentDto: CommentDto) {
        val comment = Comment()
        comment.text = commentDto.text
        comment.owner = usersRepository.findByUsername(commentDto.username) ?: throw IllegalStateException()
        comment.post = postsRepository.findById(commentDto.postId)
        commentRepository.save(comment)
    }
}