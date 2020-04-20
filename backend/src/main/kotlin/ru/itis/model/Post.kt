package ru.itis.model

import lombok.Data
import lombok.ToString
import javax.persistence.*

@Data
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
    var user: User? = null
}