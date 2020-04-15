package ru.itis

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ru.itis.config.app.ApplicationContextConfiguration
import ru.itis.model.User
import ru.itis.repositories.UsersRepository

fun  main() {
    val applicationContext = AnnotationConfigApplicationContext(ApplicationContextConfiguration::class.java)
    val usersRepository = applicationContext.getBean(UsersRepository::class.java)
    println(usersRepository)
//    usersRepository.save(User("Eldar", "1", "ldar"))
//    println(usersRepository.findByUsername("ldar").name)
}