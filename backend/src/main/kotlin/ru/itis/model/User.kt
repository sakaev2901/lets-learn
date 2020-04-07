package ru.itis.model

import lombok.Data

import javax.annotation.sql.DataSourceDefinition
import javax.persistence.*
import kotlin.properties.Delegates

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
    lateinit var name: String
    lateinit var mail: String
    lateinit var login: String
    lateinit var password: String
}