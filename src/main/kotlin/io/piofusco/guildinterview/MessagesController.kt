package io.piofusco.guildinterview

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import javax.persistence.*

interface Message {
    val content: String
}

@Entity(name = "Message")
data class MessageDTO(
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "content")
    override val content: String
) : Message

@Repository
interface MessagesRepository : JpaRepository<MessageDTO, String>

@Service
class MessagesService(var messagesRepository: MessagesRepository) {
    fun getMessages(): List<MessageDTO> {
        return messagesRepository.findAll()
    }

    fun createMessage(message: MessageDTO) {
        messagesRepository.save(message)
    }
}

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