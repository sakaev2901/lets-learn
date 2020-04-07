package ru.itis

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ru.itis.config.ApplicationContextConfiguration
import ru.itis.model.User
import ru.itis.repositories.UsersRepository
import ru.itis.repositories.UsersRepositoryJpaImpl

fun  main() {
    val applicationContext = AnnotationConfigApplicationContext(ApplicationContextConfiguration::class.java)
    val usersRepository = applicationContext.getBean(UsersRepository::class.java)
    println(usersRepository)
    usersRepository.save(User()
            .apply { name = "Eldar" }
            .apply { mail = "sakaev2901@gmail.com" }
            .apply { login = "ldark" })
    println(usersRepository.findByName("Eldar").name)
}