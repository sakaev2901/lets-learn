package ru.itis.model

import lombok.ToString
import javax.persistence.*

@Entity
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    lateinit var title: String
    lateinit var text: String
    lateinit var imageName: String

    @ManyToOne
    @ToString.Exclude
    lateinit var user: User
}