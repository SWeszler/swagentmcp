package com.sebastianweszler

import com.sebastianweszler.services.OllamaService
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

@Serializable
data class PromptRequest(val prompt: String)

fun Application.configureRouting() {
    val ollamaService: OllamaService by inject<OllamaService>()

    routing {
        post("/ollama") {
            val request = call.receive<PromptRequest>()
            val response = ollamaService.processPrompt(request.prompt)
            call.respondText(response)
        }
    }
}
