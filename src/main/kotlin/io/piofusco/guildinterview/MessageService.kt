package io.piofusco.guildinterview

import org.springframework.stereotype.Service

@Service
class MessagesService(var messagesRepository: MessagesRepository) {
    fun getMessages(): List<MessageDTO> {
        return messagesRepository.findAll()
    }

    fun createMessage(message: MessageDTO) {
        messagesRepository.save(message)
    }
}