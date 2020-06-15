package ru.itis.model

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.*
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    lateinit var text: String
    @ManyToOne
    lateinit var sender: User
    @ManyToOne
    lateinit var receiver: User
    @ManyToOne
    lateinit var chat: Chat

}