package ru.itis.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
    lateinit var text: String
    @ManyToOne
    lateinit var owner: User
    @ManyToOne
    @JsonIgnore
    lateinit var post: Post
}