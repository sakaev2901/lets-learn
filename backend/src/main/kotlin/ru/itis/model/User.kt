package ru.itis.model

import lombok.Data
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

import javax.annotation.sql.DataSourceDefinition
import javax.persistence.*
import kotlin.properties.Delegates

@Entity
@Data
@Table(name = "users")
class User : UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
    lateinit var name: String
    lateinit var mail: String
    private var username: String = ""
    private var password: String = ""
    lateinit var role: String
    @OneToMany
    lateinit var posts: List<Post>

    constructor(name: String, mail: String, username: String) {
        this.name = name
        this.mail = mail
        this.username = username
    }

    constructor()

    override fun getAuthorities() = arrayListOf(SimpleGrantedAuthority(role))

    override fun isEnabled() = true
    override fun isCredentialsNonExpired() = true
    override fun getPassword() = this.password
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun getUsername() = this.username



}