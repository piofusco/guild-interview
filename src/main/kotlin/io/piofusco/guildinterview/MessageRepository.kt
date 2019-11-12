package io.piofusco.guildinterview

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessagesRepository : JpaRepository<MessageDTO, String>