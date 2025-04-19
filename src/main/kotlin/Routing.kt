package com.sebastianweszler

import com.sebastianweszler.services.OllamaService
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sse.*
import io.ktor.sse.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val ollamaService: OllamaService by inject()
    
    install(RequestValidation) {
        validate<String> { bodyText ->
            if (!bodyText.startsWith("Hello"))
                ValidationResult.Invalid("Body text should start with 'Hello'")
            else ValidationResult.Valid
        }
    }
    install(SSE)
    install(Resources)
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/ollama") {
            val response = ollamaService.processPrompt("what time is it?", "llama3.2")
            call.respondText(response)
        }
        get<Articles> { article ->
            // Get all articles ...
            call.respond("List of articles sorted starting from ${article.sort}")
        }
        sse("/hello") {
            send(ServerSentEvent("world"))
        }
    }
}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")

