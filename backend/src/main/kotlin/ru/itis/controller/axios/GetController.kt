package ru.itis.controller.axios

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalStateException

@RestController
@RequestMapping("/api")
class GetController {

    @GetMapping("/user/fail")
    fun handleFail() {
        throw IllegalStateException()
    }

    @GetMapping("/user")
    fun handle() {
        print("ok")
    }

    @GetMapping("/user2")
    fun handle2() {
        throw IllegalStateException()       
    }
}