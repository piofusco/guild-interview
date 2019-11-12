package io.piofusco.guildinterview

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity(name = "Message")
data class MessageDTO(
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "content")
    override val content: String
) : Message