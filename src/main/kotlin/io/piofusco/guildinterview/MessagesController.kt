package io.piofusco.guildinterview

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/messages")
class MessagesController(var messagesService: MessagesService) {
    @GetMapping
    fun getMessages() = messagesService.getMessages()

    @PostMapping
    fun createMessage(
        @RequestBody message: MessageDTO
    ): ResponseEntity<MessageDTO> {
        messagesService.createMessage(message)
        return ResponseEntity(HttpStatus.CREATED)
    }
}
