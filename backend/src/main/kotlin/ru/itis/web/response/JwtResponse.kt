package ru.itis.web.response

data class JwtResponse(var token: String, var username: String, var role: String, var name: String, var surname: String, var avatar: String) {
}