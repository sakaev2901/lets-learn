package ru.itis.model

import com.google.gson.annotations.Expose
import lombok.Data
import lombok.ToString
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    lateinit var title: String
    lateinit var text: String
    lateinit var imageName: String
    @ManyToMany(fetch = FetchType.EAGER)
    var likes: MutableList<User> = LinkedList<User>()
    @ManyToOne
    @ToString.Exclude
    var user: User? = null
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
//    @JoinTable('posts_comment')
    var comments: List<Comment> =  LinkedList<Comment>()

    fun addLike(user: User) {
        if (likes == null) {
            likes = LinkedList<User>()
        }
        likes.add(user)
    }
}