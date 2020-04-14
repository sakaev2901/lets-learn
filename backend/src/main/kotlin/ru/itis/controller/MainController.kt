package ru.itis.controller

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import ru.itis.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @Autowired
    lateinit var gson:Gson

    @GetMapping("/main")
    fun getPage() = gson.toJson(User("Eldar", "ell", "d"))


    @PostMapping("/main")
    fun create(@RequestBody any: Any) {
//        println(user.name)
        println(4)
    }
}