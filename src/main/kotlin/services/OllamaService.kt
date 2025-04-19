package com.sebastianweszler.services

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class OllamaResponse(
    val model: String,
    val created_at: String,
    val response: String,
    val done: Boolean
)

class OllamaService(private val ollamaUrl: String) {
    private val client = HttpClient(CIO)
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun processPrompt(prompt: String, model: String): String {
        val response = client.post("$ollamaUrl/api/generate") {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("model", model)
                put("prompt", prompt)
            }.toString())
        }

        val responseText = response.bodyAsText()
        var fullResponse = ""
        
        // Process each line of ndjson
        responseText.lines().filter { it.isNotBlank() }.forEach { line ->
            val ollamaResponse = json.decodeFromString<OllamaResponse>(line)
            fullResponse += ollamaResponse.response
        }
        
        return fullResponse
    }
}
