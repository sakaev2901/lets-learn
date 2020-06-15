package ru.itis.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.annotations.Expose
import lombok.Data
import lombok.ToString
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

import javax.annotation.sql.DataSourceDefinition
import javax.persistence.*
import kotlin.jvm.Transient
import kotlin.properties.Delegates

@Entity
@Data
@Table(name = "users")
class User : UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
    @Expose
    lateinit var name: String
    @Expose
    lateinit var surname: String
    lateinit var mail: String
    @Expose
    private var username: String = ""
    @JsonIgnore
    private var password: String = ""
    lateinit var role: String
    lateinit var imageName: String
    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    lateinit var posts: List<Post>
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinTable(
            name = "user_friend"
    )
    @JsonIgnore
    lateinit var friends: MutableList<User>
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    lateinit var chats: MutableList<Chat>

    constructor()

    constructor(name: String, surname: String, mail: String, username: String, password: String, role: String) {
        this.mail = mail
        this.name = name
        this.password = password
        this.role = role
        this.surname = surname
        this.username = username
    }

    override fun getAuthorities() = arrayListOf(SimpleGrantedAuthority(role))

    override fun isEnabled() = true
    override fun isCredentialsNonExpired() = true
    override fun getPassword() = this.password
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun getUsername() = this.username

    fun addFriend(user: User) {
        if (friends == null) {
            friends = LinkedList<User>()
        }
        friends.add(user)
    }



}