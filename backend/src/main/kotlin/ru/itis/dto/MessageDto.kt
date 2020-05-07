package ru.itis.dto

import lombok.Data

@Data
class MessageDto {
    lateinit var text: String
    lateinit var senderUsername: String
    lateinit var receiverUsername: String
}