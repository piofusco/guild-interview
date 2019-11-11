package io.piofusco.guildinterview

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(SpringExtension::class)
@WebMvcTest(MessagesController::class)
class MessagesControllerTest {
    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun mockMessagesService() = mockk<MessagesService>(relaxed = true)
    }

    @Autowired
    lateinit var mockMessagesService: MessagesService

    @Autowired
    lateinit var subject: MessagesController

    @Autowired
    lateinit var objectMapper: ObjectMapper

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(subject)
                .build()
    }

    @Test
    fun `GET message should return ALL messages`() {
        val messages = listOf(
                MessageDTO(content = "message 1"),
                MessageDTO(content = "message 2"),
                MessageDTO(content = "message 3"),
                MessageDTO(content = "message 4"),
                MessageDTO(content = "message 5")
        )
        every { mockMessagesService.getMessages() } returns messages

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/messages")
        )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", equalTo(messages[0].content)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content", equalTo(messages[1].content)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].content", equalTo(messages[2].content)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].content", equalTo(messages[3].content)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4].content", equalTo(messages[4].content)))

        verify { mockMessagesService.getMessages() }
    }

    @Test
    fun `POST message should update messages table with new message entry`() {
        val messageToPost = MessageDTO(content = "first posted message")

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/messages")
                        .content(objectMapper.writeValueAsString(messageToPost))
                        .contentType(MediaType.APPLICATION_JSON)

        )
                .andExpect(MockMvcResultMatchers.status().isCreated)

        verify { mockMessagesService.createMessage(messageToPost) }
    }
}