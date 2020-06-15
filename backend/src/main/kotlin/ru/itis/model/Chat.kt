package ru.itis.model

import javax.persistence.*

@Entity
open class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
    var name: String = ""
    @OneToMany(fetch = FetchType.EAGER)
    lateinit var messages: MutableList<Message>
    @ManyToOne
    lateinit var firstUser: User
    @ManyToOne
    lateinit var secondUser: User
}